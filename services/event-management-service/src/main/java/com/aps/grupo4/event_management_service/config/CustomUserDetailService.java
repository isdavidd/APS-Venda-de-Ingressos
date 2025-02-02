package com.aps.grupo4.event_management_service.config;

import com.aps.grupo4.event_management_service.entity.Usuario;
import com.aps.grupo4.event_management_service.repository.UsuarioRepository;
import com.aps.grupo4.event_management_service.utils.exceptions.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCpf(cpf);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuário %s não encontrado", cpf));
        }

        return new UserPrincipal(usuario);
    }
}

