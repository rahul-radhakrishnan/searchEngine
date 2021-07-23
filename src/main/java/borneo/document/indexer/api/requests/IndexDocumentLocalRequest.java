package borneo.document.indexer.api.requests;

public class IndexDocumentLocalRequest {

    /**
     * The file path
     */
    private String path;

    /**
     * Overwrite file flag
     */
    private boolean overwrite;

    /**
     * Empty constructor for object mapper
     */
    public IndexDocumentLocalRequest() {
    }

    /**
     * Constructor
     *
     * @param path
     */
    public IndexDocumentLocalRequest(String path, boolean overwrite) {
        this.path = path;
        this.overwrite = overwrite;
    }

    /**
     * @return
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
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
