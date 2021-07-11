package borneo.document.indexer.services;

import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.SearchEngineData;
import borneo.document.indexer.models.SearchMultiQuery;
import borneo.document.indexer.models.SearchQuery;
import borneo.document.indexer.models.SearchResult;

public interface SearchEngine {

    /**
     * @param data
     * @throws ServiceException
     */
    public void insert(SearchEngineData data) throws ServiceException;

    /**
     * @param query
     * @return
     * @throws ServiceException
     */
    public SearchResult query(SearchQuery query) throws ServiceException;

    /**
     * @param query
     * @return
     * @throws ServiceException
     */
    public SearchResult query(SearchMultiQuery query) throws ServiceException;
}
