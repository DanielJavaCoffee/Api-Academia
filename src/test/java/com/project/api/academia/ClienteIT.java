package com.project.api.academia;

import com.project.api.academia.dtos.cliente.CriarClienteDto;
import com.project.api.academia.model.Cliente;
import com.project.api.academia.model.Endereco;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@Sql(scripts = "/sql/clientes/criartabela.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/endereco-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/clientes/endereco-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClienteIT {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private EntityManager entityManager;


    @Test
    @Transactional
    public void criarCliente_ComSeusAtributosValidos_RetonarClienteComStatus201() {

        UUID enderecoId = UUID.fromString("550e8200-e29b-41d4-a716-446655440056");
        Endereco endereco = entityManager.find(Endereco.class, enderecoId);

        Assertions.assertThat(endereco).isNotNull();

        CriarClienteDto clienteDto = new CriarClienteDto(
                "Fulano de Tal",
                "daniel@gmail.com",
                "123456789",
                "senha123",
                "Observação aleatória",
                LocalDate.now(),
                Cliente.Role.ROLE_CLIENTE,
                70.5,
                1.75,
                endereco
        );

        CriarClienteDto responseBody = webTestClient
                .post()
                .uri("/api/v1/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(clienteDto)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CriarClienteDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.email()).isEqualTo("daniel@gmail.com");
        Assertions.assertThat(responseBody.telefone()).isEqualTo("123456789");
    }
}


/*
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/clientes/clientes-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/endereco-insert.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "/sql/clientes/clientes-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Sql(scripts = "/sql/clientes/endereco-delete.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class ClienteIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void criarCliente_ComSeusAtributosValidos_RetonarClienteComStatus201(){

        Endereco endereco = new Endereco();
        endereco.setLogradouro("Dão Pedro |");
        endereco.setNumero("123");
        endereco.setCidade("Cidade dos Testes");

        CriarClienteDto responseBody =
                webTestClient
                .post()
                .uri("/api/v1/cliente")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new CriarClienteDto(
                        "Fulano de Tal", "daniel@gmail.com", "123456789", "senha123", "Observação aleatória", LocalDate.now(), Cliente.Role.ROLE_CLIENTE, 70.5, 1.75, endereco))
                .exchange()
                .expectStatus().isCreated()
                .expectBody(CriarClienteDto.class)
                .returnResult().getResponseBody();

        Assertions.assertThat(responseBody).isNotNull();
        Assertions.assertThat(responseBody.email()).isEqualTo("daniel@gmail.com");
        Assertions.assertThat(responseBody.telefone()).isEqualTo("123456789");

    }
}
 */
