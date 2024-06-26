package com.project.api.academia.repository;

import com.project.api.academia.dtos.cliente.ListarClienteDto;
import com.project.api.academia.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT c FROM Cliente c WHERE c.email = :email")
    Optional<Cliente> findByEmail(String email);

    @Query("SELECT c FROM Cliente c WHERE c.isAtivo = true")
    List<Cliente> findAllAtivos();

    @Query("SELECT c FROM Cliente c WHERE c.isAtivo = false")
    List<Cliente> findAllDesativados();

    Optional<Cliente> findById(UUID id);

    @Query("select u.role from Cliente u where u.email like :email")
    Cliente.Role findRoleByUsername(@Param("email") String email);


}
