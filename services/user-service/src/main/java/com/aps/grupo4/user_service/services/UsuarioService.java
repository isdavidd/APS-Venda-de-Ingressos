package com.aps.grupo4.user_service.services;


import com.aps.grupo4.user_service.entity.Usuario;
import com.aps.grupo4.user_service.repository.UsuarioRepository;
import com.aps.grupo4.user_service.validation.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> getUsuarios() {
        var usuarios = usuarioRepository.findAll();

        if (usuarios.isEmpty()) {
            return List.of();
        }

        return usuarios;
    }

    public Usuario getUsuarioPorId(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() ->
                new UsuarioNaoEncontradoException(String.format("Usuário com ID %d não encontrado", id))
        );
    }

    @Transactional
    public Usuario registerUsuario(Usuario usuario) {
        usuario.setSenha(bCryptPasswordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario updateUsuario(Usuario usuario) {
        return null;
    }

    @Transactional
    public Usuario deleteUsuario(Usuario usuario) {
        return null;
    }
}
