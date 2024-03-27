package com.project.api.academia.dtos.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StringCliente (
        @NotBlank
        @Email
        String email
){
}
