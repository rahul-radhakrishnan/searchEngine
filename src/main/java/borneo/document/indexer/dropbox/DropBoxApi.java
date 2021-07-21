package borneo.document.indexer.dropbox;


import borneo.document.indexer.enums.ServiceErrorType;
import borneo.document.indexer.exceptions.ServiceException;
import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.*;
import com.dropbox.core.v2.sharing.ListSharedLinksResult;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

/**
 * =The DropBox access Api Object.
 */
//@Component
public class DropBoxApi {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DropBoxApi.class);

    /**
     * The Request config object.
     */
    private DbxRequestConfig config;
    /**
     * The Dropbox Client object.
     */
    private DbxClientV2 client;

    /**
     * Constructor
     *
     * @param accessKey
     * @param app
     */
    @Autowired
    public DropBoxApi(@Value("${dropbox.accesskey}") String accessKey, @Value("${dropbox.app}") String app) {
        this.config = new DbxRequestConfig(app);
        this.client = new DbxClientV2(config, accessKey);
    }

    /**
     * Description: Progress listener
     *
     * @param uploaded
     * @param size
     */
    private void printProgress(long uploaded, long size) {
        logger.info("Uploaded {}  / {} bytes ({} %)", uploaded, (size), (100 * (uploaded / (double) size)));
    }

    /**
     * Description:
     *
     * @param dropboxPath
     * @param outputPath
     * @return
     * @throws ServiceException
     */
    public String downloadFile(String dropboxPath, String outputPath) throws ServiceException {
        try {
            DbxDownloader<FileMetadata> downloader = this.client.files().download(dropboxPath);
            FileOutputStream out = new FileOutputStream(outputPath);
            FileMetadata metadata = downloader.download(out);
            out.close();
            this.getShareLink(dropboxPath);
            return outputPath;
        } catch (DownloadErrorException ex) {
            logger.error("Path invalid : {}", dropboxPath, ex);
            throw new ServiceException(ServiceErrorType.INVALID_DRIVE_PATH);
        } catch (DbxException ex) {
            logger.error("Download Failed {} ", dropboxPath, ex);
            throw new ServiceException(ServiceErrorType.DROPBOX_INTERNAL_SERVER_ERROR);
        } catch (FileNotFoundException ex) {
            logger.error("Downloaded File can't be accessed {}", outputPath, ex);
            throw new ServiceException(ServiceErrorType.DOWNLOADED_FILE_NOT_ACCESSIBLE);
        } catch (IOException ex) {
            logger.error(" Download Failed. {}", dropboxPath, ex);
            throw new ServiceException(ServiceErrorType.DOWNLOAD_FAILED);
        }
    }

    /***
     * Description:
     * @param localFilePath
     * @param dropboxPath
     * @return
     * @throws ServiceException
     */

    public String uploadFile(String localFilePath, String dropboxPath) throws ServiceException {
        try {
            File localFile = new File(localFilePath);
            InputStream in = new FileInputStream(localFile);
            IOUtil.ProgressListener progressListener = l -> printProgress(l, localFile.length());
            FileMetadata metadata = this.client.files().uploadBuilder(dropboxPath)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(localFile.lastModified()))
                    .uploadAndFinish(in, progressListener);
            return this.client.sharing().createSharedLinkWithSettings(dropboxPath).getUrl();
        } catch (DbxException | IOException ex) {
            logger.error("Error uploading to Dropbox: " + ex.getMessage());
            throw new ServiceException(ServiceErrorType.UPLOAD_FAILED);
        }
    }

    /**
     * @return
     */
    public DbxClientV2 getClient() {
        return client;
    }

    /**
     * Description:
     *
     * @param dropboxPath
     * @return
     * @throws DbxException
     */
    public String getShareLink(String dropboxPath) throws DbxException {
        ListSharedLinksResult listSharedLinksResult = client.sharing()
                .listSharedLinksBuilder()
                .withPath(dropboxPath).withDirectOnly(true)
                .start();
        return (listSharedLinksResult.getLinks().size() == 0) ? this.createShareLink(dropboxPath)
                : listSharedLinksResult.getLinks().get(0).getUrl();
    }

    /**
     * Description:
     *
     * @param dropboxPath
     * @return
     * @throws DbxException
     */
    private String createShareLink(String dropboxPath) throws DbxException {
        SharedLinkMetadata sharedLinkMetadata = this.client.sharing().createSharedLinkWithSettings(dropboxPath);
        return sharedLinkMetadata.getUrl();
    }

    /**
     * Description:
     *
     * @param dropBoxPath
     * @throws ServiceException
     */
    public void deleteFile(String dropBoxPath) throws ServiceException {
        try {
            DeleteResult result = client.files().deleteV2(dropBoxPath);
            logger.info("Document deleted : {}", result.getMetadata().getParentSharedFolderId());
        } catch (DbxException e) {
            throw new ServiceException(ServiceErrorType.DOCUMENT_DELETION_FROM_DRIVE_FAILED);
        }
    }

}
