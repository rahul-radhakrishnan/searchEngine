package borneo.document.indexer.api.responses;

import borneo.document.indexer.models.SearchResult;

import java.util.List;

/**
 * Multiple keyword search Response object.
 */
public class SearchMultiKeywordApiResponse {

    /**
     * Seacrh results
     */
    private SearchResult searchResults;

    /**
     * List of keywords on the search was requested.
     */
    private List<String> keyword;

    /**
     * @param searchResults
     * @param keyword
     */
    public SearchMultiKeywordApiResponse(SearchResult searchResults, List<String> keyword) {
        this.searchResults = searchResults;
        this.keyword = keyword;
    }

    /**
     * @return
     */
    public SearchResult getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults
     */
    public void setSearchResults(SearchResult searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return
     */
    public List<String> getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }
}
