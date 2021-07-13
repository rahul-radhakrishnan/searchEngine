package borneo.document.indexer.models;

/**
 * The document search query class
 */
public class DocumentSearchQuery {

    /**
     * Document name
     */
    private String documentName;

    /**
     * @param documentName
     */
    public DocumentSearchQuery(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
}
