package borneo.document.indexer.models;

import java.util.List;

public class SearchMultiQuery {

    private List<String> keywords;

    public SearchMultiQuery(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
