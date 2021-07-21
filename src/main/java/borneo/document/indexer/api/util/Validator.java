package borneo.document.indexer.api.util;

import borneo.document.indexer.api.requests.DocumentDeleteApiRequest;
import borneo.document.indexer.api.requests.IndexDocumentDriveRequest;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.api.requests.SearchApiRequest;

import java.util.List;

/**
 * The Controller Validator class.
 */
public class Validator {

    /**
     * @param request
     * @return
     */
    public static boolean isValidSearchApiRequest(String request) {
        return (request != null && !request.isEmpty());
    }

    /**
     * Description: Validates the DocumentDeleteRequest
     *
     * @param request
     * @return True if validation passes.
     */
    public static boolean isValidDocumentDeleteApiRequest(DocumentDeleteApiRequest request) {
        return (request != null && request.getDocumentDrivePath() != null && !request.getDocumentDrivePath().isEmpty());
    }

    /**
     * Description: Validates IndexDocumentDriveRequest request.
     *
     * @param request
     * @return True if validation passes.
     */
    public static boolean isValidIndexDocumentDriveRequest(IndexDocumentDriveRequest request) {
        return (request != null) && (request.getPath() != null) && (!request.getPath().isEmpty());
    }


    /**
     * Description: Validates IndexDocumentLocalRequest request.
     *
     * @param request
     * @return True if validation passes.
     */
    public static boolean isValidIndexDocumentLocalRequest(IndexDocumentLocalRequest request) {
        return (request != null) && (request.getPath() != null) && (!request.getPath().isEmpty());
    }

    /**
     * Function checks the given argument is null or not
     *
     * @param arg1 T
     * @return true : Given Argument is null | false: Given Argument is Not null
     */
    public static <T> boolean isNull(T arg1) {
        boolean isEmpty = false;
        if (arg1 == null) {
            isEmpty = true;
        }
        return isEmpty;
    }
}
