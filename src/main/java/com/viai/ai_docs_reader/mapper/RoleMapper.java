package com.viai.ai_docs_reader.mapper;

import com.viai.ai_docs_reader.dto.response.RoleResponse;
import com.viai.ai_docs_reader.model.RoleModel;

public class RoleMapper {
    public static RoleResponse toRoleResponse(RoleModel roleModel) {
        return RoleResponse.builder()
                .roleId(roleModel.getId())
                .roleName(roleModel.getRoleName())
                .description(roleModel.getDescription())
                .build();
    }
}
