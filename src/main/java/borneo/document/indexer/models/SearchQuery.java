package borneo.document.indexer.models;

public class SearchQuery {

    private String keyword;

    public SearchQuery(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

}
