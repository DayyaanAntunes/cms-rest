package com.challenge.cms.user.domain.command;

import com.challenge.cms.user.domain.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserCommand(
        @NotBlank String name,
        @Email String email,
        @NotBlank String password,
        @NotNull Role role
) {
}
