package com.viai.ai_docs_reader.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RoleRequest {
    @JsonProperty("role_name")
    private String roleName;
    private String description;
}
