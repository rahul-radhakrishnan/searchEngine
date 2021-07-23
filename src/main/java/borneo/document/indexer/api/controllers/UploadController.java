package borneo.document.indexer.api.controllers;

import borneo.document.indexer.api.exceptions.ApiErrorType;
import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.requests.IndexDocumentLocalRequest;
import borneo.document.indexer.api.responses.UploadDocumentResponse;
import borneo.document.indexer.enums.Messages;
import borneo.document.indexer.exceptions.ServiceException;
import borneo.document.indexer.services.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static borneo.document.indexer.constants.Constants.*;

/**
 * The Controller class for the upload api.
 */
@Controller
public class UploadController {

    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

    /**
     * The index class.
     */
    @Autowired
    private Index index;

    /**
     * es type
     */
    @Value("${upload.uploadFolder}")
    private String uploadFolder;

    /**
     * Description: The method which is invoked for the upload url.
     *
     * @param request
     * @param file
     * @return
     * @throws ApiException
     * @throws ServiceException
     */
    @RequestMapping(value = (UPLOAD), consumes = {"multipart/form-data"}, method = RequestMethod.POST)
    public ResponseEntity<UploadDocumentResponse> uploadAndIndex(MultipartHttpServletRequest request,
                                                                 @RequestParam("file") MultipartFile file) throws ApiException, ServiceException {
        if (file.isEmpty()) {
            logger.error("Empty file uploaded.");
            return ResponseEntity.badRequest().body(new UploadDocumentResponse(Messages.DOCUMENT_UPLOAD_FILE_EMPTY.getMessage()));
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(this.uploadFolder + file.getOriginalFilename());
            Files.write(path, bytes);
            index.indexFromLocal(new IndexDocumentLocalRequest(path.toAbsolutePath().toString()));
        } catch (IOException e) {
            logger.error("Upload Failed.", e);
            throw new ApiException(ApiErrorType.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(new UploadDocumentResponse(Messages.DOCUMENT_UPLOAD_SUCCESS.getMessage()));
    }

}