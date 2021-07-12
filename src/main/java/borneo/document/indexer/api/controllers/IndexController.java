package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.api.util.Validator;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.api.requests.IndexDocumentDrive;
import borneo.document.indexer.api.requests.IndexDocumentLocal;
import borneo.document.indexer.services.Index;
import borneo.document.indexer.services.impl.SearchEngineImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
