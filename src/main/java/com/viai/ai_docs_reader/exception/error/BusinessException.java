package com.viai.ai_docs_reader.exception.error;

import com.viai.ai_docs_reader.exception.ErrorCode;

public class BusinessException extends RuntimeException{
    private final ErrorCode errorCode;
    private final Object[] args;

    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
        this.args = args;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Object[] getArgs() {
        return args;
    }
}
