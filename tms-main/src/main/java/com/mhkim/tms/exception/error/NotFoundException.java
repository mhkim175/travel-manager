package com.mhkim.tms.exception.error;

import com.mhkim.tms.util.MessageUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

public class NotFoundException extends RuntimeException {

    private static final String EXCEPTION_MESSAGE = "exception.message.notfound";

    private final String className;

    private final Serializable[] params;

    public NotFoundException(Class<?> cls, Serializable... params) {
        this.className = cls.getSimpleName();
        this.params = params;
    }

    @Override
    public String getMessage() {
        return MessageUtils.getMessage(EXCEPTION_MESSAGE, (params != null && params.length != 0) ? StringUtils.join(params, ",") : "", className);
    }
}
