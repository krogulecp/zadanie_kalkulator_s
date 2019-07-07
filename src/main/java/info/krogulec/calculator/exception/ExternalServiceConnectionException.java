package info.krogulec.calculator.exception;

/**
 * @author krogulecp
 */
public class ExternalServiceConnectionException extends RuntimeException {

    public ExternalServiceConnectionException(String message) {
        super(message);
    }

    public ExternalServiceConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
