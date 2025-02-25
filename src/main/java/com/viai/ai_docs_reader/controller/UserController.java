package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.UserRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.dto.response.UserResponse;
import com.viai.ai_docs_reader.mapper.UserMapper;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("id") Long id) {
        UserModel userModel = userService.getUserById(id);
        UserResponse userResponse = UserMapper.toUserResponse(userModel);
        return ApiResponse.<UserResponse>builder()
                .message("Get an user success")
                .data(userResponse)
                .build();
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getAllUsers(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<UserModel> userPage = userService.getAllUsers(pageable);

        List<UserResponse> userResponses = userPage.getContent()
                .stream()
                .map(user -> UserMapper.toUserResponse(user, user.getTimezone()))
                .toList();

        Map<String, Object> mapExtra = Map.of(
                "total_pages", userPage.getTotalPages(),
                "total_elements", userPage.getTotalElements(),
                "current_page", userPage.getNumber(),
                "page_size", userPage.getSize());

        return ApiResponse.<List<UserResponse>>builder()
                .message("Fetched users successfully")
                .data(userResponses)
                .extra(mapExtra)
                .build();
    }
}
