package com.project.api.academia.dtos.endereco;

import jakarta.validation.constraints.NotBlank;

public record CriarEnderecoDto(
        @NotBlank
        String logradouro,
        @NotBlank
        String numero,
        @NotBlank
        String bairro,
        @NotBlank
        String cidade
        ) {
}
