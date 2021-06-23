package com.mhkim.tms.exception;

import com.mhkim.tms.exception.error.AlreadyExistsException;
import com.mhkim.tms.exception.error.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GeneralExceptionHandler {

    private ResponseEntity<Error> response(String errorMessage, HttpStatus httpStatus) {
        return new ResponseEntity<>(new Error(errorMessage), httpStatus);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Error> handleNoHandlerFoundException(Exception e) {
        return response(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            IllegalStateException.class, IllegalArgumentException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class, MultipartException.class
    })
    protected ResponseEntity<Error> handleBadRequestException(Exception e) {
        log.warn("Bad request exception occurred: {}", e.getMessage(), e);
        return response(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpMediaTypeException.class)
    protected ResponseEntity<Error> handleHttpMediaTypeException(Exception e) {
        return response(e.getMessage(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<Error> handleHttpRequestMethodNotSupportedException(Exception e) {
        return response(e.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Error> handleServiceRuntimeException(RuntimeException e) {
        if(e instanceof NotFoundException) {
            return response(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        if(e instanceof AlreadyExistsException) {
            return response(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        log.warn("Unexpected service exception occurred: {}", e.getMessage(), e);
        return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Error> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
