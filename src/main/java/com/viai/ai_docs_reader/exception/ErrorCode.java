package com.viai.ai_docs_reader.exception;

public enum ErrorCode {
    USERNAME_EXISTS("error.username.exists"),
    EMAIL_EXISTS("error.email.exists"),
    RESOURCE_NOT_FOUND("error.resource.not.found"),
    INTERNAL_ERROR("error.internal");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
