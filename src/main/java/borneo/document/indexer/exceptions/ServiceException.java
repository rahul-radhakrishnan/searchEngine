package borneo.document.indexer.exceptions;

import borneo.document.indexer.enums.ServiceErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Service Exception class
 *
 * @author RahulRadhakrishnan
 */
public class ServiceException extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceException.class);

    /**
     *
     */
    private static final long serialVersionUID = 5705982646381289634L;

    /**
     * Error type
     */
    protected ServiceErrorType errorType;

    /**
     * Wrap an exception
     *
     * @param cause root cause of this exception
     */
    public ServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Manually set the type for some reason
     *
     * @param errorType DaoErrorType
     */
    public ServiceException(ServiceErrorType errorType) {
        this.errorType = errorType;
    }

	public ServiceException(String not_supported) {
    	super(not_supported);
	}

	/**
     * @return the errorType
     */
    public ServiceErrorType getErrorType() {
        return this.errorType;
    }

    /**
     * @param errorType the errorType to set
     */
    public void setErrorType(ServiceErrorType errorType) {
        this.errorType = errorType;
    }

    @Override
    public String toString() {
        String str = this.getClass().getCanonicalName();
        if (getErrorType() != null) {
            str += "(" + getErrorType().toString() + ')';
        }
        if (this.getCause() != null) {
            str += "; " + this.getCause().toString();
        }
        return str;
    }

}
