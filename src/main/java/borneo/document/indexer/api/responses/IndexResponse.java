package borneo.document.indexer.api.responses;

/**
 * Index API response
 */
public abstract class IndexResponse extends Response {

    /**
     * The Drive path.
     */
    private String drivePath;

    /**
     * The share link for the file from the dirve.
     */
    private String url;

    /**
     * Message which can be used to send any additional information.
     */
    private String message;

    /**
     * @param drivePath
     * @param url
     * @param message
     */
    public IndexResponse(String drivePath, String url, String message) {
        this.drivePath = drivePath;
        this.url = url;
        this.message = message;
    }

    /**
     * @return
     */
    public String getDrivePath() {
        return drivePath;
    }

    /**
     * @param drivePath
     */
    public void setDrivePath(String drivePath) {
        this.drivePath = drivePath;
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

    /**
     * @return
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "IndexResponse{" +
                "drivePath='" + drivePath + '\'' +
                ", url='" + url + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
