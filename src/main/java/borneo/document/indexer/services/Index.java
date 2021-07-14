package borneo.document.indexer.services;

import borneo.document.indexer.api.responses.DocumentDeleteResponse;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.api.requests.IndexDocumentDrive;
import borneo.document.indexer.api.requests.IndexDocumentLocal;
import borneo.document.indexer.models.DocumentDeleteQuery;

/**
 * The interface for the Indexing classes. The concreate classes can implement
 * whichever framework used underneath.
 */
public interface Index {


    /**
     * Description:
     *
     * @param indexDocumentLocal
     * @return
     * @throws ServiceException
     */
    public IndexFromLocalResponse indexFromLocal(IndexDocumentLocal indexDocumentLocal) throws ServiceException;

    /**
     * Description:
     *
     * @param indexDocumentDrive
     * @return
     * @throws ServiceException
     */
    public IndexFromDriveResponse indexFromDrive(IndexDocumentDrive indexDocumentDrive) throws ServiceException;

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    public DocumentDeleteResponse deleteDocument(DocumentDeleteQuery query) throws ServiceException;

}
