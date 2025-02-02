package com.aps.grupo4.user_service.config.security;

import com.aps.grupo4.user_service.entity.Usuario;
import com.aps.grupo4.user_service.repository.UsuarioRepository;
import com.aps.grupo4.user_service.validation.UsuarioNaoEncontradoException;
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
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNome(nome);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuário com CPF %s não encontrado", nome));
        }

        return new UserPrincipal(usuario);
    }
}
