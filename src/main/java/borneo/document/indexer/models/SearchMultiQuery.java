package borneo.document.indexer.models;

import java.util.List;

/**
 * The Serach query class for multiple keywords.
 */
public class SearchMultiQuery {

    /**
     * The query keywords.
     */
    private List<String> keywords;

    /**
     * Constructor
     *
     * @param keywords
     */
    public SearchMultiQuery(List<String> keywords) {
        this.keywords = keywords;
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
