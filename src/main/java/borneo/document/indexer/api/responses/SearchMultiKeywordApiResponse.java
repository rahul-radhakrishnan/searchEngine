package borneo.document.indexer.api.responses;

import borneo.document.indexer.models.SearchResult;

import java.util.List;

public class SearchMultiKeywordApiResponse {

    private SearchResult searchResults;

    private List<String> keyword;

    public SearchMultiKeywordApiResponse(SearchResult searchResults, List<String> keyword) {
        this.searchResults = searchResults;
        this.keyword = keyword;
    }

    public SearchResult getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(SearchResult searchResults) {
        this.searchResults = searchResults;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }
}
