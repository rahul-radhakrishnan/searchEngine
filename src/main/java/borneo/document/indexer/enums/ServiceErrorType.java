package borneo.document.indexer.enums;

/**
 * @author RahulRadhakrishnan
 */
public enum ServiceErrorType {

    /**
     * UNDEFINED_ERROR
     */
    UNDEFINED_ERROR("000", "Undefined error, check everything");


    /**
     * code
     */
    private final String code;
    /**
     * description
     */
    private final String description;

    /**
     * @param code        code
     * @param description description
     */
    ServiceErrorType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return code
     */
    public String getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        return this.code + ": " + this.description;
    }

    }
