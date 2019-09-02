package pl.aagenda.familyguard.datastorage.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    ErrorMessage generalExceptionHandler(Exception e) {
        log.debug("Caught unhandled exception", e);
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorMessage IllegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.debug("Illegal argument passed", e);
        return new ErrorMessage(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    ErrorMessage MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.debug("Validation exception", e);
        return new ErrorMessage(e.getMessage());
    }
}
