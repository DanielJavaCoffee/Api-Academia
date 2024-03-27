package com.project.api.academia.dtos.cliente;

import com.project.api.academia.enuns.Role;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import com.project.api.academia.model.Endereco;

import java.time.LocalDate;
import java.util.List;


public record CriarClienteDto(

        @NotBlank
        @Size(min = 10, max = 100)
        String nomeCompleto,
        @NotBlank
        @Email
        String email,
        @NotBlank
        @NotEmpty
        String telefone,

        @NotBlank
        String observacao,

        @NotNull
        LocalDate dataDeNascimento,

        @Enumerated
        Role role,

        @NotNull
        Double peso,

        @NotNull
        Double altura,

        @NotNull
        Endereco enderecos
) {

}
