package borneo.document.indexer.models;

/**
 *
 */
public class SearchEngineData {

    private String data;

    private String documentPath;

    private String documentFormat;

    private String documentName;

    private String id;

    private String url;

    /**
     * @param data
     * @param documentPath
     * @param documentFormat
     * @param documentName
     */
    public SearchEngineData(String id, String data, String documentPath, String documentFormat,
                            String documentName, String url) {
        this.id = id;
        this.data = data;
        this.documentPath = documentPath;
        this.documentFormat = documentFormat;
        this.documentName = documentName;
        this.url = url;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
