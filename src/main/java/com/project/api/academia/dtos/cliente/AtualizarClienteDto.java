package com.project.api.academia.dtos.cliente;


import com.project.api.academia.model.Cliente;

import com.project.api.academia.model.Endereco;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;


public record AtualizarClienteDto(

        @NotNull
        UUID id,
        @NotBlank
        @Size(min = 10, max = 100)
        String nomeCompleto,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefone,

        @NotBlank
        @Size(min = 6)
        String password,

        @NotBlank
        String observacao,

        @NotNull
        LocalDate dataDeNascimento,

        @Enumerated
        Cliente.Role role,

        @NotNull
        Double peso,

        @NotNull
        Double altura,

        @NotNull
        Endereco endereco
){
}
