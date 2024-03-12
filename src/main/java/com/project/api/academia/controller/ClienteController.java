package com.project.api.academia.controller;

import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
