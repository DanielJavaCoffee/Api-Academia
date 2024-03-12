package com.project.api.academia.service;

import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.model.Cliente;
import com.project.api.academia.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public CriarClienteDto salvarCliente(CriarClienteDto criarClienteDto){
        var cliente =  new Cliente();
        BeanUtils.copyProperties(criarClienteDto, cliente);
        clienteRepository.save(cliente);
        return criarClienteDto;
    }
}
