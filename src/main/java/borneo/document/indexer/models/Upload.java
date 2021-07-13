package borneo.document.indexer.models;

/**
 * The File Drive upload entity class.
 */
public class Upload {

    /**
     * The Drive path to be uploaded.
     */
    private String filePath;

    /**
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
