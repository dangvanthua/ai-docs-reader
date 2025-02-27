package com.viai.ai_docs_reader.service.token;

import com.viai.ai_docs_reader.dto.request.TokenRequest;
import com.viai.ai_docs_reader.dto.request.VerifyTokenRequest;
import com.viai.ai_docs_reader.dto.response.TokenResponse;
import com.viai.ai_docs_reader.exception.ErrorCode;
import com.viai.ai_docs_reader.exception.error.BusinessException;
import com.viai.ai_docs_reader.model.UserModel;
import com.viai.ai_docs_reader.repository.UserRepository;
import com.viai.ai_docs_reader.service.base.BaseServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl extends BaseServiceImpl<UserModel, Long, UserRepository> implements TokenService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public TokenServiceImpl(
            UserRepository repository,
            PasswordEncoder passwordEncoder,
            JwtEncoder jwtEncoder,
            JwtDecoder jwtDecoder) {
        super(repository);
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }


    @Override
    public TokenResponse createToken(TokenRequest tokenRequest) {
        if(!tokenRequest.getAuthenticated()) {
            throw new BusinessException(ErrorCode.UNAUTHENTICATED);
        }
        Instant now = Instant.now();
        Instant accessTokenExpiry = now.plus(1, ChronoUnit.HOURS);
        Instant refreshTokenExpiry = now.plus(10, ChronoUnit.DAYS);
        JwtClaimsSet accessTokenClaims = JwtClaimsSet.builder()
                .issuer("com.viai.ai_docs_reader")
                .issuedAt(now)
                .expiresAt(accessTokenExpiry)
                .subject(tokenRequest.getEmail())
                .claim("scope", createScope(tokenRequest.getRoles()))
                .build();
        String accessToken = jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();

        JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                .issuer("com.viai.ai_docs_reader")
                .issuedAt(now)
                .expiresAt(refreshTokenExpiry)
                .subject(tokenRequest.getEmail())
                .claim("token_type", "refresh")
                .build();
        String refreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public TokenResponse refreshToken(VerifyTokenRequest verifyTokenRequest) {
        try {
            Jwt decodedToken = jwtDecoder.decode(verifyTokenRequest.getRefreshToken());

            String tokenType = decodedToken.getClaimAsString("token_type");
            if (tokenType == null || !tokenType.equalsIgnoreCase("refresh")) {
                throw new BusinessException(ErrorCode.INVALID_TOKEN);
            }

            String email = decodedToken.getSubject();

            UserModel user = super.repository.findByEmail(email)
                    .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTED));

            Instant now = Instant.now();
            Instant newAccessTokenExpiry = now.plus(1, ChronoUnit.HOURS);
            Instant newRefreshTokenExpiry = now.plus(10, ChronoUnit.DAYS);

            JwtClaimsSet newAccessTokenClaims = JwtClaimsSet.builder()
                    .issuer("com.viai.ai_docs_reader")
                    .issuedAt(now)
                    .expiresAt(newAccessTokenExpiry)
                    .subject(email)
                    .claim("scope", createScope(verifyTokenRequest.getRoles()))
                    .build();
            String newAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(newAccessTokenClaims)).getTokenValue();

            JwtClaimsSet newRefreshTokenClaims = JwtClaimsSet.builder()
                    .issuer("com.viai.ai_docs_reader")
                    .issuedAt(now)
                    .expiresAt(newRefreshTokenExpiry)
                    .subject(email)
                    .claim("token_type", "refresh")
                    .build();
            String newRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(newRefreshTokenClaims)).getTokenValue();

            return TokenResponse.builder()
                    .accessToken(newAccessToken)
                    .refreshToken(newRefreshToken)
                    .build();
        } catch (Exception ex) {
            throw new BusinessException(ErrorCode.INVALID_TOKEN);
        }
    }

    private String createScope(List<String> roles) {
        return roles.stream()
                .map(role -> "ROLE_" + role.toUpperCase())
                .collect(Collectors.joining(" "));
    }
}
