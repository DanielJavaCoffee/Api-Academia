package com.project.api.academia.jwt;

import com.project.api.academia.model.Cliente;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;
public class JwtUserDetails extends User {

    private Cliente cliente;

    public JwtUserDetails(Cliente usuario) {
        super(usuario.getEmail(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.cliente = usuario;
    }

    public UUID getId() {
        return this.cliente.getId();
    }

    public String getRole() {
        return this.cliente.getRole().name();
    }
}
