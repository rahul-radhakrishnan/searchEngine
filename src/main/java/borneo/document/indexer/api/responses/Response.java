package borneo.document.indexer.api.responses;

/**
 * The Response Parent class
 *
 * @author rahul.radhakrishnan
 */
public abstract class Response {

    /**
     * Response Message
     */
    String message;

    /**
     *
     */
    public Response() {
        super();
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
