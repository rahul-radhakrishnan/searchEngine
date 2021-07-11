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


    public ApiException(ApiErrorType errorType) {
        this.errorType = errorType;
    }


    public ApiException(ApiErrorType errorType, Throwable cause) {
        super(cause);
        this.errorType = errorType;
    }

    public ApiErrorType getErrorType() {
        return this.errorType;
    }

    public void setErrorType(ApiErrorType errorType) {
        this.errorType = errorType;
    }
}
