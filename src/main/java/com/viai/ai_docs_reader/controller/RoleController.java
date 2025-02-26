package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.RoleRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.dto.response.RoleResponse;
import com.viai.ai_docs_reader.mapper.RoleMapper;
import com.viai.ai_docs_reader.model.RoleModel;
import com.viai.ai_docs_reader.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping
    public ApiResponse<RoleResponse> createRole(@RequestBody RoleRequest roleRequest) {
        RoleModel roleModel = roleService.createRole(roleRequest);
        RoleResponse roleResponse = RoleMapper.toRoleResponse(roleModel);
        return ApiResponse.<RoleResponse>builder()
                .message("Create role success")
                .data(roleResponse)
                .build();
    }
}
