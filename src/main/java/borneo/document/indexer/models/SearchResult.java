package borneo.document.indexer.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchResult {

    private Set<Document> results;

    public SearchResult() {
        this.results = new HashSet<>();
    }

    public Set<Document> getResults() {
        return results;
    }

}
