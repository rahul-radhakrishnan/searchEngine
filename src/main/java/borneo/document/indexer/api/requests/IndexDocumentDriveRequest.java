package borneo.document.indexer.api.requests;

/**
 * The Index Document from drive http request entity class.
 */
public class IndexDocumentDriveRequest {

    /**
     * The file path
     */
    private String path;

    /**
     * Empty constructor for object mapper
     */
    public IndexDocumentDriveRequest() {
    }

    /**
     * Constructor
     *
     * @param path
     */
    public IndexDocumentDriveRequest(String path) {
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
