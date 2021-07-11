package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.requests.SearchApiRequest;
import borneo.document.indexer.api.responses.SearchApiResponse;
import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.responses.SearchMultiKeywordApiResponse;
import borneo.document.indexer.api.util.Validators;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.SearchMultiQuery;
import borneo.document.indexer.models.SearchQuery;
import borneo.document.indexer.models.SearchResult;
import borneo.document.indexer.services.SearchEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private SearchEngine searchEngine;


 //   @GetMapping(value = "/search/{keyword}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SearchApiResponse> query(HttpServletRequest request, @PathVariable(value = "keyword") String keyword) throws ApiException {
        if (!Validators.isValidSearchApiRequest(keyword)) {
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        try {
            SearchResult result = this.searchEngine.query(new SearchQuery(keyword));
            return ResponseEntity.ok(new SearchApiResponse(result, keyword));

        } catch (ServiceException ex) {
            throw new ApiException(ApiErrorType.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/search/{keywords}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<SearchMultiKeywordApiResponse> queryKeywords(HttpServletRequest httpRequest, @PathVariable(value = "keywords") String keywords) throws ApiException {

        List<String> keywordList = Arrays.asList(keywords.split("\\+"));
        try {
            SearchResult result = this.searchEngine.query(new SearchMultiQuery(keywordList));
            return ResponseEntity.ok(new SearchMultiKeywordApiResponse(result, keywordList));

        } catch (ServiceException ex) {
            throw new ApiException(ApiErrorType.INTERNAL_SERVER_ERROR);
        }
    }
}
