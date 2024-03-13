package com.project.api.academia.dtos.cliente;


import com.project.api.academia.model.Cliente;

import com.project.api.academia.enuns.Role;
import com.project.api.academia.model.Endereco;

import java.time.LocalDate;
import java.util.List;

public record ClienteListDto(
        String nomeCompleto,
        String email,
        String telefone,
        String observacao,
        LocalDate dataDeNascimento,
        Role role,
        Double peso,
        Double altura,
        List<Endereco> enderecos
) {
    public ClienteListDto(Cliente cliente) {
        this(
                cliente.getNomeCompleto(),
                cliente.getEmail(),
                cliente.getNumeroDeTelefone(),
                cliente.getObservacao(),
                cliente.getDataDeNascimento(),
                cliente.getRole(),
                cliente.getPeso(),
                cliente.getAltura(),
                cliente.getEnderecos()
        );
    }
}

