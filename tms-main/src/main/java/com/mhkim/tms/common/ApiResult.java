package com.mhkim.tms.common;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ApiResult<T> {

    @ApiModelProperty(value = "API 요청 처리 결과", required = true)
    private final boolean success;

    @ApiModelProperty(value = "API 요청 처리 응답 값")
    private final T data;

    @ApiModelProperty(value = "API 요청 처리 상태 코드")
    private final int status;

    public static <T> ApiResult<T> ok(HttpStatus status) {
        return new ApiResult<>(true, null, status.value());
    }
    
    public static <T> ApiResult<T> ok(T data, HttpStatus status) {
        return new ApiResult<>(true, data, status.value());
    }

    public static ApiResult<?> error(String errorMessage, HttpStatus status) {
        return new ApiResult<>(false, errorMessage, status.value());
    }

    public static ApiResult<?> error(Throwable throwable, HttpStatus status) {
        return new ApiResult<>(false, throwable, status.value());
    }

}
