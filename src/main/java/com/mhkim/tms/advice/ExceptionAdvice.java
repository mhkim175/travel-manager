package com.mhkim.tms.advice;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private ResponseEntity<?> response(String errorMessage, HttpStatus status) {
        return new ResponseEntity<>(errorMessage, status);
    }

    @ExceptionHandler(CDataNotFoundException.class)
    protected ResponseEntity<?> dataNotFoundException(Exception e) {
        return response(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return response(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
