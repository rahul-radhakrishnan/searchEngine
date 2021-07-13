package borneo.document.indexer.models;

/**
 * The indexing data for the Serach Engine.
 */
public class SearchEngineData {

    /**
     * Data content
     */
    private String data;

    /**
     * The document drive path
     */
    private String documentPath;

    /**
     * The document format (pdf/docx)
     */
    private String documentFormat;

    /**
     * The document name
     */
    private String documentName;

    /**
     * The indexer id.
     */
    private String id;

    /**
     * File share link.
     */
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

    /**
     * @return
     */
    public String getData() {
        return data;
    }

    /**
     * @param data
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return
     */
    public String getDocumentPath() {
        return documentPath;
    }

    /**
     * @param documentPath
     */
    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    /**
     * @return
     */
    public String getDocumentFormat() {
        return documentFormat;
    }

    /**
     * @param documentFormat
     */
    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    /**
     * @return
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
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
}
