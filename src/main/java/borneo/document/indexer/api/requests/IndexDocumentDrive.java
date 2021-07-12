package borneo.document.indexer.api.requests;

/**
 * The request class for the indexing from the drive flow.
 */
public class IndexDocumentDrive {

    /**
     * The file path in the drive.
     */
    private String path;

    /**
     * Constructor
     *
     * @param path
     */
    public IndexDocumentDrive(String path) {
        this.path = path;
    }

    /**
     * Default Constructor
     */
    public IndexDocumentDrive() {
    }

    /**
     * @return String
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
     * @return String
     */
    @Override
    public String toString() {
        return "IndexDocumentDrive{" +
                "path='" + path + '\'' +
                '}';
    }
}
