package borneo.document.indexer.services.impl;

import borneo.document.indexer.dropbox.DropBoxApi;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.services.DriveApi;
import com.dropbox.core.DbxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 *
 */
@Component
public class DriveApiImpl implements DriveApi {

    @Autowired
    private DropBoxApi dropBoxApi;

    @Override
    public String download(String drivePath, String filePath) throws ServiceException {
        String outputFilePath = null;
        try {
            outputFilePath = this.dropBoxApi.downloadFile(drivePath, filePath);
        } catch (IOException | DbxException ex) {
            throw new ServiceException(ex);
        }
        return outputFilePath;
    }

    @Override
    public String upload(String filePath, String drivePath) throws ServiceException {
        try {
            return this.dropBoxApi.uploadFile(filePath, drivePath);
        } catch (DbxException | IOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public String getDownloadLink(String dropboxPath) throws ServiceException {
        try {
            return this.dropBoxApi.getShareLink(dropboxPath);
        } catch (DbxException e) {
            throw new ServiceException(e);
        }
    }
}
