package borneo.document.indexer.services;

import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.IndexDocumentDrive;
import borneo.document.indexer.models.IndexDocumentLocal;

public interface Index {


    /**
     * @param indexDocumentLocal
     * @return
     * @throws ServiceException
     */
    public IndexFromLocalResponse indexFromLocal(IndexDocumentLocal indexDocumentLocal) throws ServiceException;

    /**
     * @param indexDocumentDrive
     * @return
     * @throws ServiceException
     */
    public IndexFromDriveResponse indexFromDrive(IndexDocumentDrive indexDocumentDrive) throws ServiceException;

}
