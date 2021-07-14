package borneo.document.indexer.api.requests;

/**
 * The Delete document controller request.
 */
public class DocumentDeleteApiRequest {

    /**
     * The document drive path
     */
    private String documentDrivePath;

    /**
     * Empty constructor
     */
    public DocumentDeleteApiRequest() {
    }


    /**
     * @param documentDrivePath
     */
    public DocumentDeleteApiRequest(String documentDrivePath) {
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
