package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.responses.SearchMultiKeywordApiResponse;
import borneo.document.indexer.api.util.Validator;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.SearchMultiQuery;
import borneo.document.indexer.models.SearchResult;
import borneo.document.indexer.services.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * The controller class for the Search endpoints
 */
@Controller
public class SearchController {

    @Autowired
    private SearchEngine searchEngine;


    /**
     * Description : Description: The endpoint with /search/keywords/{keywords}. {keywords} is a path variable.
     *
     * @param httpRequest
     * @param keywords
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @GetMapping(value = "/search/{keywords}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SearchMultiKeywordApiResponse> queryKeywords(HttpServletRequest httpRequest, @PathVariable(value = "keywords") String keywords)
            throws ApiException, ServiceException {
        if (!Validator.isValidSearchApiRequest(keywords)) {
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }

        List<String> keywordList = Arrays.asList(keywords.split("\\+"));
        SearchResult result = this.searchEngine.query(new SearchMultiQuery(keywordList));
        return ResponseEntity.ok(new SearchMultiKeywordApiResponse(result, keywordList));
    }

}
