package com.viai.ai_docs_reader.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class RoleResponse {
    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("role_name")
    private String roleName;

    private String description;
}
