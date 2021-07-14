package borneo.document.indexer.api.responses;

/**
 *
 */
public class DocumentDeleteResponse {

    /**
     * Doc id
     */
    private String id;

    /**
     * Message string
     */
    private String message;

    /**
     * @param id
     * @param message
     */
    public DocumentDeleteResponse(String id, String message) {
        this.id = id;
        this.message = message;
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
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
