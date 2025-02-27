package com.viai.ai_docs_reader.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class VerifyTokenRequest {
    @JsonProperty("refresh_token")
    private String refreshToken;

    private List<String> roles;
}
