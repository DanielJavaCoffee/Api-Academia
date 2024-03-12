package com.project.api.academia.service;

import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.model.Cliente;
import com.project.api.academia.model.Endereco;
import com.project.api.academia.repository.ClienteRepository;
import com.project.api.academia.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private final EnderecoRepository enderecoRepository;

    @Transactional
    public CriarClienteDto salvarCliente(CriarClienteDto criarClienteDto) {
        var cliente = new Cliente();
        BeanUtils.copyProperties(criarClienteDto, cliente);

        if (cliente.getEnderecos() != null) {
            for (Endereco endereco : cliente.getEnderecos()) {
                endereco.setCliente(cliente);
            }
        }
        clienteRepository.save(cliente);
        return criarClienteDto;
    }

}
