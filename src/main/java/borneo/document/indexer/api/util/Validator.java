package borneo.document.indexer.api.util;

import borneo.document.indexer.api.requests.DocumentDeleteApiRequest;
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
        if (request == null || request.isEmpty())
            return false;
        return true;
    }

    /**
     * @param request
     * @return
     */
    public static boolean isValidMultiSearchApiRequest(SearchApiRequest request) {
        if (request == null || request.getKeywords() == null || request.getKeywords().isEmpty())
            return false;
        return true;
    }

    /**
     * @param request
     * @return
     */
    public static boolean isValidDocumentDeleteApiRequest(DocumentDeleteApiRequest request) {
        if (request == null || request.getDocumentDrivePath() == null || request.getDocumentDrivePath().isEmpty())
            return false;
        return true;
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
