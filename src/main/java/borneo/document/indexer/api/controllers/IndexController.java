package borneo.document.indexer.api.controllers;


import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.requests.DocumentDeleteApiRequest;
import borneo.document.indexer.api.requests.IndexDocumentDriveRequest;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.api.responses.DocumentDeleteResponse;
import borneo.document.indexer.api.responses.IndexFromDriveResponse;
import borneo.document.indexer.api.responses.IndexFromLocalResponse;
import borneo.document.indexer.api.util.Validator;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.models.DocumentDeleteQuery;
import borneo.document.indexer.services.Index;
import org.apache.commons.io.FilenameUtils;
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

import static borneo.document.indexer.constants.Constants.*;

/**
 * The controller class for the indexing Apis. The endpoints for indexing files from local
 * or directly downloading from the drive.
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
     * @param indexDocumentLocalRequest
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @PostMapping(value = INDEX_FROM_LOCAL, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromLocalResponse> indexDocumentFromLocal(@RequestBody IndexDocumentLocalRequest indexDocumentLocalRequest)
            throws ApiException, ServiceException {
        if (!Validator.isValidIndexDocumentLocalRequest(indexDocumentLocalRequest)) {
            logger.error("Invalid request paramaters {}", indexDocumentLocalRequest);
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        IndexFromLocalResponse response = index.indexFromLocal(indexDocumentLocalRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Description: The controller class for the indexing of the file from the drive.
     *
     * @param indexDocumentDriveRequest
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @PostMapping(value = INDEX_FROM_DRIVE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<IndexFromDriveResponse> indexDocumentFromDrive(@RequestBody IndexDocumentDriveRequest indexDocumentDriveRequest)
            throws ApiException, ServiceException {
        if (!Validator.isValidIndexDocumentDriveRequest(indexDocumentDriveRequest)) {
            logger.error("Invalid request paramaters {}", indexDocumentDriveRequest);
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }
        IndexFromDriveResponse response = index.indexFromDrive(indexDocumentDriveRequest);
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
    @DeleteMapping(value = DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<DocumentDeleteResponse> deleteDocument(HttpServletRequest httpRequest, @RequestBody DocumentDeleteApiRequest request)
            throws ApiException, ServiceException {
        if (!Validator.isValidDocumentDeleteApiRequest(request)) {
            logger.error("Invalid request paramaters {}", request);
            throw new ApiException(ApiErrorType.INVALID_REQUEST_PARAMETERS);
        }

        DocumentDeleteResponse result = this.index.deleteDocument(new DocumentDeleteQuery(FilenameUtils
                .getName(request.getDocumentDrivePath()),
                FilenameUtils.getFullPath(request.getDocumentDrivePath())));
        return ResponseEntity.ok(new DocumentDeleteResponse(result.getDocumentPath(), result.getMessage()));
    }
}
