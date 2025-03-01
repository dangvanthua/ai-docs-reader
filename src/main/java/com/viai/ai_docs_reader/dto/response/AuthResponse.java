package com.viai.ai_docs_reader.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class AuthResponse {
    private Boolean authenticated;
}
