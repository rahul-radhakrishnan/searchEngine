package borneo.document.indexer.services;

import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.*;

/**
 * The Serach Engiune interface.
 */
public interface SearchEngine {

    /**
     * Description:
     *
     * @param data
     * @throws ServiceException
     */
    public void insert(SearchEngineData data) throws ServiceException;

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    public SearchResult query(SearchQuery query) throws ServiceException;

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    public SearchResult query(SearchMultiQuery query) throws ServiceException;

    /**
     * Description:
     *
     * @param query
     * @return
     * @throws ServiceException
     */
    public boolean deleteDocument(DocumentDeleteQuery query) throws ServiceException;
}
