package com.viai.ai_docs_reader.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthRequest {
    @NotBlank(message = "{user.email.notblank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    @NotBlank(message = "{user.password.notblank}")
    @Size(min = 6, message = "{user.password.size}")
    private String password;
}
