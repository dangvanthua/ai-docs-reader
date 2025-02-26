package com.viai.ai_docs_reader.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class AuthResponse {
    private Boolean authenticated;
}
