package borneo.document.indexer.models;

import java.util.Objects;

public class Document {

    private String documentName;

    private String documentFormat;

    private String documentUrl;

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentFormat() {
        return documentFormat;
    }

    public void setDocumentFormat(String documentFormat) {
        this.documentFormat = documentFormat;
    }

    public String getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
    }

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
