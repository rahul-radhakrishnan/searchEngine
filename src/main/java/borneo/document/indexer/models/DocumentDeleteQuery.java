package borneo.document.indexer.models;

/**
 * Tge document delete query class.
 */
public class DocumentDeleteQuery {

    /**
     * The document drive path
     */
    private String documentDrivePath;

    /**
     * Document name
     */
    private String documentName;


    /**
     * @param documentDrivePath
     */
    public DocumentDeleteQuery(String documentName, String documentDrivePath) {
        this.documentName = documentName;
        this.documentDrivePath = documentDrivePath;
    }

    /**
     * @return
     */
    public String getDocumentDrivePath() {
        return documentDrivePath;
    }

    /**
     * @param documentDrivePath
     */
    public void setDocumentDrivePath(String documentDrivePath) {
        this.documentDrivePath = documentDrivePath;
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
