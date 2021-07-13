package borneo.document.indexer.enums;

import org.springframework.http.HttpStatus;

/**
 * @author RahulRadhakrishnan
 */
public enum ServiceErrorType {

    /**
     * UNDEFINED_ERROR
     */
    UNDEFINED_ERROR("000", "Undefined error, check everything", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_FILE_PATH("001", "Invalid File Path", HttpStatus.NOT_FOUND),
    UNSUPPORTED_FORMAT("002", "Unsupported file format", HttpStatus.BAD_REQUEST),
    PARSER_EXCEPTION("003", "File Parsing Failed.", HttpStatus.INTERNAL_SERVER_ERROR),
    INDEXING_FAILED("004", "Indexing Failed", HttpStatus.INTERNAL_SERVER_ERROR),
    SEARCH_QUERY_FAILED("005", "Search Query Failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DOWNLOAD_FAILED("006", "File Download Failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DROPBOX_INTERNAL_SERVER_ERROR("007", "DropBox Server Exception", HttpStatus.INTERNAL_SERVER_ERROR),
    DOWNLOADED_FILE_NOT_ACCESSIBLE("008", "Downloaded File Path Corrupted/Non-accessible", HttpStatus.INTERNAL_SERVER_ERROR),
    UPLOAD_FAILED("009", "Upload to drive failed", HttpStatus.INTERNAL_SERVER_ERROR),
    DOWNLOAD_LINK_CREATION_FAILED("010", "Shared Link creation failed", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_DRIVE_PATH("011", "File not exist in drive.", HttpStatus.BAD_REQUEST),
    DOCUMENT_ALREADY_EXISTS("012", "Document Already Indexed",  HttpStatus.ALREADY_REPORTED);

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
    private ServiceErrorType(String code, String description, HttpStatus httpStatus) {
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
