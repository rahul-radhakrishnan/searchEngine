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
     * Document Path
     */
    private String documentPath;

    /**
     * @param documentName
     */
    public DocumentSearchQuery(String documentName, String documentPath) {
        this.documentPath = documentPath;
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

    /**
     * @return
     */
    public String getDocumentPath() {
        return documentPath;
    }

    /**
     * @param documentPath
     */
    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
}
