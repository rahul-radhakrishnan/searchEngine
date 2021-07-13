package borneo.document.indexer.models;

/**
 * The search query entity class for a single keyword
 */
public class SearchQuery {

    /**
     * The keyword to be searched.
     */
    private String keyword;

    /**
     * @param keyword
     */
    public SearchQuery(String keyword) {
        this.keyword = keyword;
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

}
