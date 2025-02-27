package com.viai.ai_docs_reader.service.role;

import com.viai.ai_docs_reader.dto.request.RoleRequest;
import com.viai.ai_docs_reader.model.RoleModel;

import java.util.List;

public interface RoleService {
    RoleModel createRole(RoleRequest roleRequest);
    void deleteRole(Long roleId);
    List<RoleModel> getAllRole();
    RoleModel getByRoleName(String roleName);
}
