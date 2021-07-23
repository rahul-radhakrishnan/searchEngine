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
     *
     */
    private boolean overwrite;

    /**
     * Constructor
     *
     * @param metadata
     */
    public UploadDocumentApiRequest(String metadata, boolean overwrite) {
        this.overwrite = overwrite;
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

    /**
     * @return
     */
    public boolean isOverwrite() {
        return overwrite;
    }

    /**
     * @param overwrite
     */
    public void setOverwrite(boolean overwrite) {
        this.overwrite = overwrite;
    }
}
