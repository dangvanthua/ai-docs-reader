package com.viai.ai_docs_reader.exception;

import java.util.Map;

public record ErrorResponse(
        int statusCode,
        String message,
        long timestamp,
        Map<String, String> errors) {
}
