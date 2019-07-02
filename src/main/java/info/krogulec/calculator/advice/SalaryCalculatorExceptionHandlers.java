package info.krogulec.calculator.advice;

import info.krogulec.calculator.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Only basic demo
 *
 * @author krogulecp
 */
@RestControllerAdvice
public class SalaryCalculatorExceptionHandlers {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        //Only basic functionality for demonstration purposes
        return new ResponseEntity<>(new ExceptionResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
