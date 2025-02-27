package com.viai.ai_docs_reader.service.token;

import com.viai.ai_docs_reader.dto.request.TokenRequest;
import com.viai.ai_docs_reader.dto.request.VerifyTokenRequest;
import com.viai.ai_docs_reader.dto.response.TokenResponse;

public interface TokenService {
    TokenResponse createToken(TokenRequest tokenRequest);
    TokenResponse refreshToken(VerifyTokenRequest verifyTokenRequest);
}
