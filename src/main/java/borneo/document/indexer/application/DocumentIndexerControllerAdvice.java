package borneo.document.indexer.application;

import borneo.document.indexer.api.exceptions.ApiException;
import borneo.document.indexer.api.responses.ErrorResponse;
import borneo.document.indexer.exceptions.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Spring controller advice class for Exception Handling. The framework will create ExceptionHandler
 * from all the methods with the @ExceptionHandler and uses it to handle all the exceptions thrown over the controllers.
 *
 * @author rahul.radhakrishnan
 */
@ControllerAdvice
public class DocumentIndexerControllerAdvice {

    private static final Logger logger = LoggerFactory
            .getLogger(DocumentIndexerControllerAdvice.class);

    /**
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiExceptionHandler(ApiException ex) {
        logger.error(ex.getErrorType().getDescription(), ex);
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
        logger.error("Code: " + ex.getErrorType().getCode() + " description :" + ex.getErrorType().getDescription());
        ErrorResponse response = new ErrorResponse(ex.getErrorType().getDescription());
        return new ResponseEntity<ErrorResponse>(response, ex.getErrorType().getHttpStatus());
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
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(new Object(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Handle HTTP-header Content-Type unsupported exception
     *
     * @param ex caught exception
     * @return expectedResponse entity
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseBody
    public ResponseEntity<Object> handleHttpMediaTypeNotSupportedException(
            HttpMediaTypeNotSupportedException ex) {
        logger.error(ex.getMessage(), ex);
        return new ResponseEntity<Object>(new Object(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handle json conversion exception
     *
     * @param ex HttpMessageNotReadableException
     * @return ResponseEntity
     */
    @SuppressWarnings("unused")
    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        Throwable t = ex.getCause();
        String exceptionName = t.getClass().getName();
        String extraMessage = t.getMessage();
        return new ResponseEntity<>(new ErrorResponse("Json mapping Failed"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle the unknown exceptions
     *
     * @param ex Exception
     * @return the response entity with error object
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        logger.error(ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse("Internal Server Error");
        return new ResponseEntity<ErrorResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
