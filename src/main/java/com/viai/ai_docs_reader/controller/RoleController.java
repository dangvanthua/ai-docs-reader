package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.RoleRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.dto.response.RoleResponse;
import com.viai.ai_docs_reader.mapper.RoleMapper;
import com.viai.ai_docs_reader.model.RoleModel;
import com.viai.ai_docs_reader.service.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @DeleteMapping("/{roleId}")
    public ApiResponse<Void> deleteRoleById(@PathVariable("roleId") Long id) {
        roleService.deleteRole(id);
        return ApiResponse.<Void>builder()
                .message("Delete role success")
                .build();
    }

    @GetMapping
    private ApiResponse<List<RoleResponse>> getAllRole() {
        List<RoleModel> roleModels = roleService.getAllRole();
        List<RoleResponse> roleResponses = roleModels.stream()
                .map(RoleMapper::toRoleResponse)
                .toList();
        return ApiResponse.<List<RoleResponse>>builder()
                .message("Get all roles success")
                .data(roleResponses)
                .build();
    }
}
