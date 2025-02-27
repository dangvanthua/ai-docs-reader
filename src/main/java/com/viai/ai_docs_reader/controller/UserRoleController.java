package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.UserRoleRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.service.user_role.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user_roles")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;

    @PostMapping
    public ApiResponse<Void> createUserRole(@RequestBody UserRoleRequest userRoleRequest) {
        userRoleService.createUserRole(userRoleRequest);
        return ApiResponse.<Void>builder()
                .message("Create user role success")
                .build();
    }
}
