package borneo.document.indexer.api.responses;

/**
 * The error response class
 *
 * @author rahul.radhakrishnan
 */
public class ErrorResponse extends Response {
    /**
     * @param message
     */
    public ErrorResponse(String message) {
        super();
        this.message = message;
    }

    /**
     * @return String
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
