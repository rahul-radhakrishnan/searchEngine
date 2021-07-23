package borneo.document.indexer.api.requests;

/**
 *
 */
public class UploadDocumentApiRequest {

    /**
     * File metadata
     */
    private String metadata;

    /**
     * Constructor
     *
     * @param metadata
     */
    public UploadDocumentApiRequest(String metadata) {
        this.metadata = metadata;
    }

    /**
     * Constructor
     */
    public UploadDocumentApiRequest() {
    }

    /**
     * @return
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * @param metadata
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }
}
