package com.project.api.academia.service;

import com.project.api.academia.dtos.cliente.AtualizarClienteDto;
import com.project.api.academia.dtos.cliente.ListarClienteDto;
import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.exception.ClienteNotFound;
import com.project.api.academia.exception.ClienteUniqueViolationException;
import com.project.api.academia.model.Cliente;
import com.project.api.academia.model.Endereco;
import com.project.api.academia.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public CriarClienteDto salvarCliente(CriarClienteDto criarClienteDto) {
       try {
           var cliente = new Cliente();
           BeanUtils.copyProperties(criarClienteDto, cliente);

           if (cliente.getEnderecos() != null) {
               for (Endereco endereco : cliente.getEnderecos()) {
                   endereco.setCliente(cliente);
               }
           }
           clienteRepository.save(cliente);
           return criarClienteDto;
       } catch (DataIntegrityViolationException exception){
           throw new ClienteUniqueViolationException("Já existe um cliente cadastrado. " + exception.getMessage());
       }
    }

    @Transactional
    public List<ListarClienteDto> getAll(){
        return clienteRepository.findAll().stream().map(ListarClienteDto::new).toList();
    }

    @Transactional(readOnly = true)
    public ListarClienteDto findByID(Long id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if(clienteOptional.isPresent()){
            var cliente  = new ListarClienteDto(clienteOptional.get());
            return cliente;
        }
        throw new ClienteNotFound("Cliente não encontrado.");
    }

    @Transactional
    public AtualizarClienteDto atualizarCliente(AtualizarClienteDto atualizarClienteDto){

        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findById(atualizarClienteDto.id()).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado.")));
        var cliente = clienteOptional.get();
        BeanUtils.copyProperties(atualizarClienteDto, cliente, "id", "enderecos");
        if (cliente.getEnderecos() != null) {
            for (Endereco endereco : cliente.getEnderecos()) {
                endereco.setCliente(cliente);
            }
        }
        clienteRepository.save(cliente);
        return atualizarClienteDto;
    }
}
