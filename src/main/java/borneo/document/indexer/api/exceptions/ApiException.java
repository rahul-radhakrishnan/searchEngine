package borneo.document.indexer.api.exceptions;

public class ApiException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 4433109240867055161L;
    /**
     * errorType
     */
    protected ApiErrorType errorType;


    /**
     * @param errorType
     */
    public ApiException(ApiErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * @param errorType
     * @param cause
     */

    public ApiException(ApiErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }

    /**
     * @return
     */
    public ApiErrorType getErrorType() {
        return this.errorType;
    }

    /**
     * @param errorType
     */
    public void setErrorType(ApiErrorType errorType) {
        this.errorType = errorType;
    }
}
