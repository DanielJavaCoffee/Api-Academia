package com.project.api.academia.dtos.cliente;


import com.project.api.academia.model.Cliente;

import com.project.api.academia.enuns.Role;
import com.project.api.academia.model.Endereco;

import java.time.LocalDate;
import java.util.List;

public record ListarClienteDto(
        String nomeCompleto,
        String email,
        String telefone,
        Boolean isAtivo,
        String observacao,
        LocalDate dataDeNascimento,
        Role role,
        Double peso,
        Double altura,
        Endereco enderecos
) {
    public ListarClienteDto(Cliente cliente) {
        this(
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

