
package borneo.document.indexer.api.exceptions;


import org.springframework.http.HttpStatus;

/**
 * The API error type.
 */
public enum ApiErrorType {

    UNDEFINED_ERROR("000", "Undefined error", HttpStatus.INTERNAL_SERVER_ERROR),
    REQUEST_OBJECT_NULL("001", "Request Body Null", HttpStatus.BAD_REQUEST),
    INVALID_REQUEST_PARAMETERS("002", "Invalid Request Parameters", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("003", "Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR),
    FILE_EMPTY_ERROR("004", "File is empty", HttpStatus.BAD_REQUEST);

    /**
     * Error code
     */
    private final String code;
    /**
     * Error description
     */
    private final String description;

    /**
     * Http status
     */
    private final HttpStatus httpStatus;

    /**
     * @param code
     * @param description
     * @param httpStatus
     */
    ApiErrorType(String code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    /**
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return String
     */
    public String getCode() {
        return this.code;
    }

    /**
     * @return HttpStatus
     */
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    /**
     * toString method
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Error: [ ");
        builder.append("  " + "code" + ": ").append(this.code);
        builder.append(",  " + "description" + ": ").append(this.description);
        builder.append(" ]");
        return builder.toString();
    }
}
