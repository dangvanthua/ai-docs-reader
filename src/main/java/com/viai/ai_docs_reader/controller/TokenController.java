package com.viai.ai_docs_reader.controller;

import com.viai.ai_docs_reader.dto.request.TokenRequest;
import com.viai.ai_docs_reader.dto.request.VerifyTokenRequest;
import com.viai.ai_docs_reader.dto.response.ApiResponse;
import com.viai.ai_docs_reader.dto.response.TokenResponse;
import com.viai.ai_docs_reader.service.token.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final TokenService tokenService;

    @PostMapping
    public ApiResponse<TokenResponse> createToken(@RequestBody TokenRequest tokenRequest) {
        TokenResponse tokenResponse = tokenService.createToken(tokenRequest);
        return ApiResponse.<TokenResponse>builder()
                .message("Create token success")
                .data(tokenResponse)
                .build();
    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refreshToken(@RequestBody VerifyTokenRequest verifyTokenRequest) {
        TokenResponse tokenResponse = tokenService.refreshToken(verifyTokenRequest);
        return ApiResponse.<TokenResponse>builder()
                .message("Refresh token success")
                .data(tokenResponse)
                .build();
    }
}
