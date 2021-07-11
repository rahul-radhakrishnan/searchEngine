package borneo.document.indexer.api.requests;

import java.util.List;

public class SearchApiRequest {

    private List<String> keywords;

    public SearchApiRequest(List<String> keywords) {
        this.keywords = keywords;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords;
    }
}
