package borneo.document.indexer.application;

import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.responses.ErrorResponse;
import borneo.document.indexer.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Spring controller advice class for Exception Handling
 *
 * @author rahul.radhakrishnan
 */
@ControllerAdvice
public class DocumentIndexerControllerAdvice {

    private static final Logger LOG = LoggerFactory
            .getLogger(DocumentIndexerControllerAdvice.class);

    /**
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiExceptionHandler(ApiException ex) {
        LOG.error(ex.getErrorType().getDescription(), ex);
        ErrorResponse response = new ErrorResponse(ex.getErrorType().getDescription());
        return new ResponseEntity<ErrorResponse>(response, ex.getErrorType().getHttpStatus());
    }

    /**
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorResponse> serviceLayerExceptionHandler(ServiceException ex) {
        LOG.error("Code: " + ex.getErrorType().getCode() + " description :" + ex.getErrorType().getDescription());
        ErrorResponse response = new ErrorResponse(ex.getErrorType().getDescription());
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle the http method not supported exception
     *
     * @param ex HttpRequestMethodNotSupportedException
     * @return the response entity with error object
     */
    @ResponseBody
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> handleHttpMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(new Object(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle the unknown exceptions
     *
     * @param ex Exception
     * @return the response entity with error object
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        LOG.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(new Object(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
