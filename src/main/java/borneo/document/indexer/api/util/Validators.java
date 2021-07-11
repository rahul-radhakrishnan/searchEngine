package borneo.document.indexer.api.util;

import borneo.document.indexer.api.requests.SearchApiRequest;

import java.util.List;

public class Validators {

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
}
