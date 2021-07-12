package borneo.document.indexer.api.requests;

/**
 * The Inex from local path request class.
 */
public class IndexDocumentLocal {

    /**
     * Local Path
     */
    private String path;

    /**
     * @param path
     */
    public IndexDocumentLocal(String path) {
        this.path = path;
    }

    /**
     * Constructor
     */
    public IndexDocumentLocal() {
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
    @Override
    public String toString() {
        return "IndexDocumentLocal{" +
                "path='" + path + '\'' +
                '}';
    }
}
