package com.project.api.academia.jwt;


import com.project.api.academia.model.Cliente;
import com.project.api.academia.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final ClienteService clienteService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cliente usuario = clienteService.buscarPorUsername(username);
        return new JwtUserDetails(usuario);
    }

    public JwtToken getTokenAuthenticated(String username) {
        Cliente.Role role = clienteService.buscarRolePorUsername(username);
        return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));
    }
}