package info.krogulec.calculator.model;

import java.time.LocalDateTime;

/**
 * Only basic demo
 *
 * @author krogulecp
 */
public final class ExceptionResponse {

    private final String message;
    private final LocalDateTime timestamp;

    public ExceptionResponse(String message) {
        this.message = message;
        timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
