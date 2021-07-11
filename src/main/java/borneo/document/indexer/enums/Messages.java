package borneo.document.indexer.enums;

public enum Messages {


    INDEX_FROM_DRIVE_SUCCESS("Indexing from Drive success"),
    INDEX_FROM_LOCAL_SUCCESS("Indexing from Local success");


    /**
     * message
     */
    private final String message;


    Messages(String message) {
        this.message = message;
    }

    /**
     * @return code
     */
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.message;
    }

}

