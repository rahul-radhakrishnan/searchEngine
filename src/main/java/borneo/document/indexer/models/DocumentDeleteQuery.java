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
     * @param documentDrivePath
     */
    public DocumentDeleteQuery(String documentDrivePath) {
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
}
