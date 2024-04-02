package com.project.api.academia.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioLoginDto(

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Size(min = 6)
        String password
) {
}
