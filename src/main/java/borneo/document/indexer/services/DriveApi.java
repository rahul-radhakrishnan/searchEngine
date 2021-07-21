package borneo.document.indexer.services;

import borneo.document.indexer.exceptions.ServiceException;
import com.dropbox.core.DbxException;

import java.io.File;
import java.io.IOException;

/**
 * The interface for the Drive access (GDrive, DropBox etc)
 */
public interface DriveApi {

    /**
     * Description:
     *
     * @param drivePath
     * @param filePath
     * @return
     * @throws ServiceException
     */
    public String download(String drivePath, String filePath) throws ServiceException;

    /**
     * Description:
     *
     * @param filePath
     * @param drivePath
     * @return
     * @throws ServiceException
     */
    public String upload(String filePath, String drivePath) throws ServiceException;


    /**
     * Description:
     *
     * @param dropboxPath
     * @return
     * @throws ServiceException
     */
    public void deleteFile(String dropboxPath) throws ServiceException;

    /**
     * Description:
     *
     * @param dropboxPath
     * @return
     * @throws ServiceException
     */
    public String getDownloadLink(String dropboxPath) throws ServiceException;
}
