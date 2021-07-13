package borneo.document.indexer.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Search Results class.
 */
public class SearchResult {

    /**
     * Additional message on the extracted documents
     */
    private String message;

    /**
     * The set of documents as retreived as part of the query results.
     */
    private Set<Document> results;

    /**
     *
     */
    public SearchResult() {
        this.results = new HashSet<>();
    }

    /**
     * @return
     */
    public Set<Document> getResults() {
        return results;
    }

    /**
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
