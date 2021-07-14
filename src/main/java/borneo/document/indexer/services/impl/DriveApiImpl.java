package borneo.document.indexer.services.impl;

import borneo.document.indexer.dropbox.DropBoxApi;
import borneo.document.indexer.enums.ServiceErrorType;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.services.DriveApi;
import com.dropbox.core.DbxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DriveApiImpl implements DriveApi {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(DriveApiImpl.class);

    /**
     * Dropbox Api access object.
     */
    @Autowired
    private DropBoxApi dropBoxApi;

    /**
     * Description:
     *
     * @param drivePath
     * @param filePath
     * @return
     * @throws ServiceException
     */
    @Override
    public String download(String drivePath, String filePath) throws ServiceException {
        logger.info("Download started {} to {}", drivePath, filePath);
        String outputFilePath = this.dropBoxApi.downloadFile(drivePath, filePath);
        logger.info("File Downloaded to {} ", filePath);
        return outputFilePath;
    }

    /**
     * Description:
     *
     * @param filePath
     * @param drivePath
     * @return
     * @throws ServiceException
     */
    @Override
    public String upload(String filePath, String drivePath) throws ServiceException {
        logger.info("Upload started from  {} to  {}", filePath, drivePath);
        return this.dropBoxApi.uploadFile(filePath, drivePath);
    }

    /**
     * Description:
     *
     * @param dropboxPath
     * @throws ServiceException
     */
    @Override
    public void deleteFile(String dropboxPath) throws ServiceException {
        this.dropBoxApi.deleteFile(dropboxPath);
        logger.info("Deletion success : {}", dropboxPath);
    }

    /**
     * Description:
     *
     * @param dropboxPath
     * @return
     * @throws ServiceException
     */
    @Override
    public String getDownloadLink(String dropboxPath) throws ServiceException {
        try {
            return this.dropBoxApi.getShareLink(dropboxPath);
        } catch (DbxException e) {
            throw new ServiceException(ServiceErrorType.DOWNLOAD_LINK_CREATION_FAILED);
        }
    }
}
