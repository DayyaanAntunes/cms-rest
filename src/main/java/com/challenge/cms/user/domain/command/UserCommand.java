package com.challenge.cms.user.domain.command;

import com.challenge.cms.user.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCommand(
        @NotBlank(message = "{user.name-missing}") String name,
        @Email(message = "{user.email-invalid}") String email,
        @NotBlank(message = "{user.password-missing}") String password,
        @NotNull(message = "{user.role-missing}") Role role
) {
}
