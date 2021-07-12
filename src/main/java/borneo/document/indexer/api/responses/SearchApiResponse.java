package borneo.document.indexer.api.responses;

import borneo.document.indexer.models.SearchResult;

/**
 * The Search Api Response class.
 */
public class SearchApiResponse extends Response {

    /**
     * The search results
     */
    private SearchResult searchResults;

    /**
     * The keyword for which the search was made.
     */
    private String keyword;

    /**
     * @param searchResults
     * @param keyword
     */
    public SearchApiResponse(SearchResult searchResults, String keyword) {
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
    public String getKeyword() {
        return keyword;
    }

    /**
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "SearchApiResponse{" +
                "searchResults=" + searchResults +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
