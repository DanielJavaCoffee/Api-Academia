package com.project.api.academia.controller;

import com.project.api.academia.dtos.cliente.AtualizarClienteDto;
import com.project.api.academia.dtos.cliente.ListarClienteDto;
import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.dtos.cliente.StringCliente;
import com.project.api.academia.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<CriarClienteDto> salvarCliente(@RequestBody @Valid CriarClienteDto criarClienteDto){
        var cliente = clienteService.salvarCliente(criarClienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ListarClienteDto>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListarClienteDto> buscarCliente(@PathVariable @Valid Long id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByID(id));
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<ListarClienteDto>> listarTodosOsClientesAtivos(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodosOsClientesAtivos());
    }

    @GetMapping("/desativados")
    public ResponseEntity<List<ListarClienteDto>> listarTodosOsClientesDesativados(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodosOsClientesDesativados());
    }

    @PutMapping()
    public ResponseEntity<AtualizarClienteDto> atualizarCliente(@RequestBody @Valid AtualizarClienteDto atualizarClienteDto){
        var cliente = clienteService.atualizarCliente(atualizarClienteDto);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @PatchMapping("/desativar")
    public ResponseEntity<Void> desativarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.desativarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/ativar")
    public ResponseEntity<Void> ativarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.AtivarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.deletarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
