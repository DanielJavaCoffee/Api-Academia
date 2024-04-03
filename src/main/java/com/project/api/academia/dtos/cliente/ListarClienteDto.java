package com.project.api.academia.dtos.cliente;


import com.project.api.academia.model.Cliente;
import com.project.api.academia.model.Endereco;
import java.time.LocalDate;
import java.util.UUID;

public record ListarClienteDto(

        UUID id,
        String nomeCompleto,
        String email,
        String telefone,
        Boolean isAtivo,
        String observacao,
        LocalDate dataDeNascimento,
        Cliente.Role role,
        Double peso,
        Double altura,
        Endereco enderecos
) {
    public ListarClienteDto(Cliente cliente) {
        this(
                cliente.getId(),
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getTelefone(),
                cliente.getIsAtivo(),
                cliente.getObservacao(),
                cliente.getDataDeNascimento(),
                cliente.getRole(),
                cliente.getPeso(),
                cliente.getAltura(),
                cliente.getEndereco()
        );
    }
}

