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
    private List<String> keywords;

    /**
     * @param searchResults
     * @param keyword
     */
    public SearchMultiKeywordApiResponse(SearchResult searchResults, List<String> keyword) {
        this.searchResults = searchResults;
        this.keywords = keyword;
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
    public List<String> getKeywords() {
        return keywords;
    }

    /**
     * @param keywords
     */
    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
