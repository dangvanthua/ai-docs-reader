package com.viai.ai_docs_reader.exception.error;

public class ResourceNotFoundException extends RuntimeException {
    private final String messageCode;
    private final Object[] args;

    public ResourceNotFoundException(String messageCode, Object... args) {
        super(messageCode);
        this.messageCode = messageCode;
        this.args = args;
    }

    public String getMessageCode() {
        return messageCode;
    }
    public Object[] getArgs() {
        return args;
    }
}
