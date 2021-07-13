package borneo.document.indexer.models;

import java.util.Objects;

/**
 * The Document model class which stores the query results.
 */
public class Document {

    /**
     * Document name.
     */
    private String documentName;

    /**
     * The document format(pdf/docx)
     */
    private String documentFormat;

    /**
     * The document share link
     */
    private String documentUrl;

    /**
     * @return String
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    /**
     * @return
     */
    public String getDocumentFormat() {
        return documentFormat;
    }

    /**
     * @param documentFormat
     */
    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    /**
     * @return
     */
    public String getDocumentUrl() {
        return documentUrl;
    }

    /**
     * @param documentUrl
     */
    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document)) return false;
        Document document = (Document) o;
        return Objects.equals(getDocumentName(), document.getDocumentName()) &&
                Objects.equals(getDocumentFormat(), document.getDocumentFormat()) &&
                Objects.equals(getDocumentUrl(), document.getDocumentUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDocumentName(), getDocumentFormat(), getDocumentUrl());
    }
}
