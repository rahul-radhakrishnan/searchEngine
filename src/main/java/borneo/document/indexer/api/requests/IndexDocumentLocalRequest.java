package borneo.document.indexer.api.requests;

public class IndexDocumentLocalRequest {

    /**
     * The file path
     */
    private String path;

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
    public IndexDocumentLocalRequest(String path) {
        this.path = path;
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
}
