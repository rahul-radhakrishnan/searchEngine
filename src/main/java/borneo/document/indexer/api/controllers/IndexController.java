package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.requests.DocumentDeleteApiRequest;
import borneo.document.indexer.api.responses.DocumentDeleteResponse;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.api.responses.SearchMultiKeywordApiResponse;
import borneo.document.indexer.api.util.Validator;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.api.requests.IndexDocumentDrive;
import borneo.document.indexer.api.requests.IndexDocumentLocal;
import borneo.document.indexer.models.DocumentDeleteQuery;
import borneo.document.indexer.models.SearchMultiQuery;
import borneo.document.indexer.models.SearchResult;
import borneo.document.indexer.services.Index;
import borneo.document.indexer.services.impl.SearchEngineImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * The controller class for the indexing Apis. The endpoints for indexing files from local
 * or directly downloading from the dropbox.
 */
@Controller
public class IndexController {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * The index class.
     */
    @Autowired
    private Index index;

    /**
     * Description: The controller class for the local file indexing url.
     *
     * @param indexDocumentLocal
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @PostMapping(value = "/indexLocal", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromLocalResponse> indexDocumentFromLocal(@RequestBody IndexDocumentLocal indexDocumentLocal)
            throws ApiException, ServiceException {
        if (Validator.isNull(indexDocumentLocal) || Validator.isNull(indexDocumentLocal.getPath()) || indexDocumentLocal.getPath().isEmpty()) {
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        IndexFromLocalResponse response = index.indexFromLocal(indexDocumentLocal);
        return ResponseEntity.ok(response);
    }

    /**
     * Description: The controller class for the indexing of the file from the drive.
     *
     * @param indexDocumentDrive
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @PostMapping(value = "/indexDrive", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromDriveResponse> indexDocumentFromDrive(@RequestBody IndexDocumentDrive indexDocumentDrive)
            throws ApiException, ServiceException {
        if (Validator.isNull(indexDocumentDrive) || Validator.isNull(indexDocumentDrive.getPath()) || indexDocumentDrive.getPath().isEmpty()) {
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        IndexFromDriveResponse response = null;
        response = index.indexFromDrive(indexDocumentDrive);
        return ResponseEntity.ok(response);
    }


    /**
     * Description: The controller class for the deletion of the file from the serach engine and the drive.
     *
     * @param httpRequest
     * @param request
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DocumentDeleteResponse> deleteDocument(HttpServletRequest httpRequest, @RequestBody DocumentDeleteApiRequest request)
            throws ApiException, ServiceException {
        if (!Validator.isValidDocumentDeleteApiRequest(request)) {
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        DocumentDeleteResponse result = this.index.deleteDocument(new DocumentDeleteQuery(request.getDocumentDrivePath()));
        return ResponseEntity.ok(new DocumentDeleteResponse(result.getId(), result.getMessage()));
    }

}
