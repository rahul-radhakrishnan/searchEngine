package borneo.document.indexer.api.responses;

/**
 * The Indexing from the local path API response.
 */
public class IndexFromLocalResponse extends IndexResponse {

    /**
     * The file path
     */
    private String localPath;

    /**
     * @param localPath
     * @param drivePath
     * @param url
     * @param message
     */
    public IndexFromLocalResponse(String localPath, String drivePath, String url, String message) {
        super(drivePath, url, message);
        this.localPath = localPath;
    }


    /**
     * @return
     */
    public String getLocalPath() {
        return localPath;
    }

    /**
     * @param localPath
     */
    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public String toString() {
        return "IndexFromLocalResponse{" +
                "localPath='" + localPath + '\'' +
                '}' + "\n" + super.toString();
    }
}
