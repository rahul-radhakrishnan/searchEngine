package borneo.document.indexer.dropbox;


import com.dropbox.core.DbxDownloader;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.util.IOUtil;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.dropbox.core.v2.sharing.ListSharedLinksResult;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Date;

@Component
public class DropBoxApi {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DropBoxApi.class);

    private DbxRequestConfig config;
    private DbxClientV2 client;

    @Autowired
    public DropBoxApi(@Value("${dropbox.accesskey}") String accessKey, @Value("${dropbox.app}") String app) {
        this.config = new DbxRequestConfig(app);
        this.client = new DbxClientV2(config, accessKey);
    }

    /**
     * @param uploaded
     * @param size
     */
    private void printProgress(long uploaded, long size) {
        logger.info("Uploaded %12d / %12d bytes (%5.2f%%)" + uploaded + (size) + (100 * (uploaded / (double) size)));
    }

    /**
     * @param dropboxPath
     * @param outputPath
     * @return
     * @throws DbxException
     * @throws IOException
     */
    public String downloadFile(String dropboxPath, String outputPath) throws DbxException, IOException {
        DbxDownloader<FileMetadata> downloader = this.client.files().download(dropboxPath);
        try {
            FileOutputStream out = new FileOutputStream(outputPath);
            FileMetadata metadata = downloader.download(out);
            out.close();
        } catch (DbxException | FileNotFoundException ex) {
            logger.error(ex.getMessage());
            throw ex;
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw e;
        }

        this.getFileDownloadUrl(dropboxPath);
        return outputPath;
    }

    /**
     * @param localFilePath
     * @param dropboxPath
     * @return
     * @throws DbxException
     * @throws IOException
     */

    public String uploadFile(String localFilePath, String dropboxPath) throws DbxException, IOException {
        File localFile = new File(localFilePath);
        SharedLinkMetadata sharedLinkMetadata = null;
        try {
            InputStream in = new FileInputStream(localFile);
            IOUtil.ProgressListener progressListener = l -> printProgress(l, localFile.length());

            FileMetadata metadata = this.client.files().uploadBuilder(dropboxPath)
                    .withMode(WriteMode.ADD)
                    .withClientModified(new Date(localFile.lastModified()))
                    .uploadAndFinish(in, progressListener);
            sharedLinkMetadata = this.client.sharing().createSharedLinkWithSettings(dropboxPath);

            logger.info(metadata.toStringMultiline());
        } catch (DbxException ex) {
            logger.error("Error uploading to Dropbox: " + ex.getMessage());
            throw ex;
        } catch (IOException ex) {
            logger.error("Error reading from file \"" + localFile + "\": " + ex.getMessage());
            throw ex;
        }
        return sharedLinkMetadata.getUrl();
    }

    /**
     * @return
     */
    public DbxClientV2 getClient() {
        return client;
    }

    /**
     * @param dropboxPath
     * @return
     * @throws DbxException
     */
    public String getFileDownloadUrl(String dropboxPath) throws DbxException {
        ListSharedLinksResult listSharedLinksResult = client.sharing()
                .listSharedLinksBuilder()
                .withPath(dropboxPath).withDirectOnly(true)
                .start();
        String url = listSharedLinksResult.getLinks().get(0).getUrl();
        return url;
    }

}
