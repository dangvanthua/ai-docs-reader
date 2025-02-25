package com.viai.ai_docs_reader.dto.response;

import lombok.*;

import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponse<T> {
    private String message;
    private T data;
    private Map<String, Object> extra;
}
