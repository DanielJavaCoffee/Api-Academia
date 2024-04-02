package com.project.api.academia.controller;

import com.project.api.academia.dtos.cliente.AtualizarClienteDto;
import com.project.api.academia.dtos.cliente.ListarClienteDto;
import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.dtos.cliente.StringCliente;
import com.project.api.academia.erro.ErrorMessage;
import com.project.api.academia.exception.ClienteNotFound;
import com.project.api.academia.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cliente")
@RequiredArgsConstructor
@Tag(name = "Cliente Controller")
public class ClienteController {

    private final ClienteService clienteService;

    @Operation(summary = "Criar um novo Cliente.", description = "Recurso para criar um novo Cliente.",
            responses = {
            @ApiResponse(responseCode = "201", description = "Rescurso criado com sucesso!",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CriarClienteDto.class))),

            @ApiResponse(responseCode = "409", description = "Usuário já cadastrado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),

            @ApiResponse(responseCode = "422", description = "Dados de entradas inválidos.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
            }
    )
    @PostMapping
    public ResponseEntity<CriarClienteDto> salvarCliente(@RequestBody @Valid CriarClienteDto criarClienteDto){
        var cliente = clienteService.salvarCliente(criarClienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
    }

    @Operation(summary = "Listar todos os clientes.", description = "Recurso para listar todos os clientes.",
            responses = {
            @ApiResponse( responseCode = "200", description = "Recurso acessado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListarClienteDto.class)))
            }
    )
    @GetMapping
    public ResponseEntity<List<ListarClienteDto>> listarClientes(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.getAll());
    }

    @Operation(summary = "Buscar Cliente por id.", description = "Recurso para busca de cliente por id.",
            responses = {
            @ApiResponse(responseCode = "200", description = "Recurso acessado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListarClienteDto.class))
            ),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteNotFound.class))
            ),
            @ApiResponse(responseCode = "400", description = "Solicitação inválida.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ListarClienteDto> buscarCliente(@PathVariable @Valid UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.findByID(id));
    }

    @Operation(summary = "Listar todos os clientes ativos.", description = "Recurso para listar todos os clientes ativos.",
            responses = {
                    @ApiResponse( responseCode = "200", description = "Recurso acessado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListarClienteDto.class)))
            }
    )
    @GetMapping("/ativos")
    public ResponseEntity<List<ListarClienteDto>> listarTodosOsClientesAtivos(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodosOsClientesAtivos());
    }

    @Operation(summary = "Listar todos os clientes desativados.", description = "Recurso para listar todos os clientes desativados.",
            responses = {
                    @ApiResponse( responseCode = "200", description = "Recurso acessado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ListarClienteDto.class)))
            }
    )
    @GetMapping("/desativados")
    public ResponseEntity<List<ListarClienteDto>> listarTodosOsClientesDesativados(){
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodosOsClientesDesativados());
    }

    @Operation(summary = "Atualizar Cliente.", description = "Recurso para atualizar cliente.",
            responses = {
            @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarClienteDto.class))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteNotFound.class))),
            @ApiResponse(responseCode = "422", description = "Dados de entradas inválidos.",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
    })
    @PutMapping()
    public ResponseEntity<AtualizarClienteDto> atualizarCliente(@RequestBody @Valid AtualizarClienteDto atualizarClienteDto){
        var cliente = clienteService.atualizarCliente(atualizarClienteDto);
        return ResponseEntity.status(HttpStatus.OK).body(cliente);
    }

    @Operation(summary = "Desativar Cliente.", description = "Recurso para desativar cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarClienteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteNotFound.class)))
            })
    @PatchMapping("/desativar")
    public ResponseEntity<Void> desativarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.desativarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Ativar Cliente.", description = "Recurso para ativar cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarClienteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteNotFound.class)))
            })
    @PatchMapping("/ativar")
    public ResponseEntity<Void> ativarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.AtivarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "Excluir Cliente.", description = "Recurso para excluir cliente.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso realizado com sucesso.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = AtualizarClienteDto.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteNotFound.class)))
            })
    @DeleteMapping()
    public ResponseEntity<Void> deletarCliente(@RequestBody @Valid StringCliente stringCliente){
        clienteService.deletarCliente(stringCliente.email());
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
