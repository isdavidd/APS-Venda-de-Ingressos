package com.aps.grupo4.user_service.services;


import com.aps.grupo4.user_service.entity.Roles;
import com.aps.grupo4.user_service.entity.Usuario;
import com.aps.grupo4.user_service.entity.dto.UsuarioDTO;
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

    public Usuario getUsuarioPorCpf(String cpf) {
        var usuario = usuarioRepository.findByCpf(cpf);

        if (usuario == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuário com CPF %s não encontrado", cpf));
        }

        return usuario;
    }

    @Transactional
    public Usuario registerUsuario(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));

       var usuario = Usuario.builder()
                .nome(usuarioDTO.getNome())
                .cpf(usuarioDTO.getCpf())
                .email(usuarioDTO.getEmail())
                .telefone(usuarioDTO.getTelefone())
                .senha(usuarioDTO.getSenha())
                .role(Roles.USER)
                .build();

        if (usuarioDTO.getNome().contains("admin")) {
            usuario.setRole(Roles.ADMIN);
        }

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public String updateUsuario(UsuarioDTO usuarioDTO) {

        var usuarioExistente = usuarioRepository.findByCpf(usuarioDTO.getCpf());

        if (usuarioExistente == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuário com o CPF %s não foi encontrado. Não ocorreu atualização"));
        }

        var linhasAfetadas = usuarioRepository.updateByCpf(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getTelefone(),
                usuarioDTO.getCpf()
        );

        if (linhasAfetadas != 1) {
            throw new RuntimeException(String.format("Ocorreu um erro na atualização do usuário de CPF %s", usuarioDTO.getCpf()));
        }

        return "Usuário Atualizado com sucesso.";

    }

    @Transactional
    public String deleteUsuario(String cpf) {
        var usuarioExistente = usuarioRepository.findByCpf(cpf);

        if (usuarioExistente == null) {
            throw new UsuarioNaoEncontradoException(String.format("Usuário com CPF %s não encontrado. Não ocorreu deleção.", cpf));
        }

        var linhasAfetadas = usuarioRepository.deleteByCpf(cpf);

        if (linhasAfetadas != 1) {
            throw new RuntimeException(String.format("Ocorreu um erro na deleção do usuário de CPF %s", cpf));
        }

        return "Usuário deletado com sucesso.";

    }
}
