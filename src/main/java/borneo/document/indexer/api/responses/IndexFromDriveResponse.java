package borneo.document.indexer.api.responses;

/**
 * The Index From Drive API response
 */
public class IndexFromDriveResponse extends IndexResponse {

    /**
     * Constructor
     *
     * @param drivePath
     * @param url
     * @param message
     */
    public IndexFromDriveResponse(String drivePath, String url, String message) {
        super(drivePath, url, message);
    }

    /**
     * @return
     */

    @Override
    public String toString() {
        return "IndexFromDriveResponse{}" + super.toString();
    }
}
