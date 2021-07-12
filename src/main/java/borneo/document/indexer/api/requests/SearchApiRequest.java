package borneo.document.indexer.api.requests;

import java.util.List;

/**
 * The Seracj API request class.
 */
public class SearchApiRequest {

    /**
     * The list of keywords to be searched.
     */
    private List<String> keywords;

    /**
     * Constructor
     *
     * @param keywords
     */
    public SearchApiRequest(List<String> keywords) {
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

    /**
     * @return
     */
    @Override
    public String toString() {
        return "SearchApiRequest{" +
                "keywords=" + keywords +
                '}';
    }
}
