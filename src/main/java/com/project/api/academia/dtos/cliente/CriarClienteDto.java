package com.project.api.academia.dtos.cliente;

import com.project.api.academia.enuns.Role;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record CriarClienteDto(

        @NotBlank
        @Size(min = 10, max = 100)
        String nomeCompleto,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String numeroDeTelefone,

        @Enumerated
        Role role,

        @NotNull
        Double peso,

        @NotNull
        Double altura
) {

}
