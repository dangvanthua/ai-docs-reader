package com.viai.ai_docs_reader.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenRequest {
    private String email;
    private Boolean authenticated;
}
