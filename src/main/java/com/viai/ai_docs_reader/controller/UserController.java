package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.UserRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.dto.response.UserResponse;
import com.viai.ai_docs_reader.mapper.UserMapper;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserModel userModel = userService.createUser(userRequest);
        UserResponse userResponse = UserMapper.toUserResponse(userModel, userModel.getTimezone());
        return ApiResponse.<UserResponse>builder()
                .message("Register user success")
                .data(userResponse)
                .build();
    }
}
