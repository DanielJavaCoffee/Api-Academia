package com.project.api.academia.service;

import com.project.api.academia.dtos.cliente.AtualizarClienteDto;
import com.project.api.academia.dtos.cliente.ListarClienteDto;
import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.exception.ClienteNotFound;
import com.project.api.academia.exception.ClienteUniqueViolationException;
import com.project.api.academia.model.Cliente;
import com.project.api.academia.repository.ClienteRepository;
import com.project.api.academia.repository.EnderecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class ClienteService {
    private final ClienteRepository clienteRepository;
    private final EnderecoRepository enderecoRepository;

    @Transactional
    public CriarClienteDto salvarCliente(CriarClienteDto criarClienteDto) {
       try {
           var cliente = new Cliente();
           var endereco = criarClienteDto.enderecos();
           BeanUtils.copyProperties(criarClienteDto, cliente);
           enderecoRepository.save(endereco);
           cliente.setEndereco(endereco);
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
    public List<ListarClienteDto> listarTodosOsClientesAtivos(){
        return clienteRepository.findAllAtivos().stream().map(ListarClienteDto::new).toList();
    }

    @Transactional(readOnly = true)
    public List<ListarClienteDto> listarTodosOsClientesDesativados(){
        return clienteRepository.findAllDesativados().stream().map(ListarClienteDto::new).toList();
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

        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findByEmail(atualizarClienteDto.email()).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado.")));
        var cliente = clienteOptional.get();
        BeanUtils.copyProperties(atualizarClienteDto, cliente);
        clienteRepository.save(cliente);
        return atualizarClienteDto;
    }

    @Transactional
    public void desativarCliente(String email) {
        try {
            log.info("Desativando cliente com o email: {}", email);
            Optional<Cliente> clienteOptional = clienteRepository.findByEmail(email);

            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                cliente.setIsAtivo(false);
                clienteRepository.save(cliente);
                log.info("Cliente com o email {} desativado com sucesso.", email);
            } else {
                log.error("Cliente com o email {} não encontrado.", email);
                throw new ClienteNotFound("Cliente não encontrado");
            }
        } catch (Exception e) {
            log.error("Ocorreu um erro ao desativar o cliente com o email {}:", email, e);
            throw e;
        }
    }

    @Transactional
    public void AtivarCliente(String email){
        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findByEmail(email)).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado"));
        clienteOptional.ifPresent(cliente -> {cliente.setIsAtivo(true);
            clienteRepository.save(cliente);
        });
    }

    @Transactional
    public void deletarCliente(String email){
        Optional<Cliente> clienteOptional = Optional.ofNullable(clienteRepository.findByEmail(email)).orElseThrow(() -> new ClienteNotFound("Cliente não encontrado"));
        clienteRepository.delete(clienteOptional.get());
    }
}
