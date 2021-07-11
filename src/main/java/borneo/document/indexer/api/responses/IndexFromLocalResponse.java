package borneo.document.indexer.api.responses;

public class IndexFromLocalResponse extends IndexResponse {

    private String localPath;

    public IndexFromLocalResponse(String localPath, String drivePath, String url, String message) {
        super(drivePath, url, message);
        this.localPath = localPath;
    }


    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
