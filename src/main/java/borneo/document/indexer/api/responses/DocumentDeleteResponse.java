package borneo.document.indexer.api.responses;

/**
 *
 */
public class DocumentDeleteResponse extends Response {

    /**
     * Doc id
     */
    private String documentPath;


    /**
     * @param documentPath
     * @param message
     */
    public DocumentDeleteResponse(String documentPath, String message) {
        this.documentPath = documentPath;
        this.message = message;
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
