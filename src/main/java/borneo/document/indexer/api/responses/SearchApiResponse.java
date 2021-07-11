package borneo.document.indexer.api.responses;

import borneo.document.indexer.models.SearchResult;

public class SearchApiResponse extends Response {

    private SearchResult searchResults;

    private String keyword;

    public SearchApiResponse(SearchResult searchResults, String keyword) {
        this.searchResults = searchResults;
        this.keyword = keyword;
    }

    public SearchResult getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(SearchResult searchResults) {
        this.searchResults = searchResults;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
