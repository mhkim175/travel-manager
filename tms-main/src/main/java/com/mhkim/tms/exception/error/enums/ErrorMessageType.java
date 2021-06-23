package com.mhkim.tms.exception.error.enums;

public enum ErrorMessageType {

    FORMAT("error.format"),

    NULL("error.null");

    private final String value;

    ErrorMessageType(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

}
