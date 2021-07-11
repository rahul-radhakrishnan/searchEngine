package borneo.document.indexer.api.responses;

public class IndexResponse extends Response {

    private String drivePath;

    private String url;

    private String message;

    public IndexResponse(String drivePath, String url, String message) {
        this.drivePath = drivePath;
        this.url = url;
        this.message = message;
    }

    public String getDrivePath() {
        return drivePath;
    }

    public void setDrivePath(String drivePath) {
        this.drivePath = drivePath;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
