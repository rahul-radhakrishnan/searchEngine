package borneo.document.indexer.models;

/**
 * The File Parser data entity class.
 */
public class ParserData {

    /**
     * Data content
     */
    private String data;

    /**
     * File type
     */
    private String type;

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
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }
}
