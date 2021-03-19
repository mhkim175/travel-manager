package com.mhkim.tms.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mhkim.tms.advice.exception.CDataNotFoundException;
import com.mhkim.tms.common.ApiResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private ResponseEntity<ApiResult<?>> response(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ApiResult.error(throwable, status), headers, status);
    }

    @ExceptionHandler(CDataNotFoundException.class)
    protected ResponseEntity<?> dataNotFoundException(Exception e) {
        return response(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ Exception.class, RuntimeException.class })
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Unexpected exception occurred: {}", e.getMessage(), e);
        return response(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
