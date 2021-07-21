package borneo.document.indexer.services;

import borneo.document.indexer.api.responses.DocumentDeleteResponse;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.api.requests.IndexDocumentDriveRequest;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.models.DocumentDeleteQuery;

/**
 * The interface for the Indexing classes. The concreate classes can implement
 * whichever framework used underneath.
 */
public interface Index {


    /**
     * Description:
     *
     * @param indexDocumentLocalRequest
     * @return
     * @throws ServiceException
     */
    public IndexFromLocalResponse indexFromLocal(IndexDocumentLocalRequest indexDocumentLocalRequest) throws ServiceException;

    /**
     * Description:
     *
     * @param indexDocumentDriveRequest
     * @return
     * @throws ServiceException
     */
    public IndexFromDriveResponse indexFromDrive(IndexDocumentDriveRequest indexDocumentDriveRequest) throws ServiceException;

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    public DocumentDeleteResponse deleteDocument(DocumentDeleteQuery query) throws ServiceException;

}
