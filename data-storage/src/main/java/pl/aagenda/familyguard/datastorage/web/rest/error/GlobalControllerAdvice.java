package pl.aagenda.familyguard.datastorage.web.rest.error;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(Exception.class)
    ErrorMessage generalExceptionHandler(Exception e) {
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorMessage IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        return new ErrorMessage(e.getMessage());
    }
}
