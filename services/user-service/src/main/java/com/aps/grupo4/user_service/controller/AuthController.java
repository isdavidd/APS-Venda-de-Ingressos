package com.aps.grupo4.user_service.controller;

import com.aps.grupo4.user_service.entity.dto.UsuarioLoginRequest;
import com.aps.grupo4.user_service.entity.dto.UsuarioLoginResponse;
import com.aps.grupo4.user_service.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Object> generateBasicToken(@RequestBody UsuarioLoginRequest request) {

        var usuario = usuarioService.getUsuarioPorCpf(request.getCpf());

        if (usuario == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(404)).build();
        }

        String auth = request.getCpf() + ":" + request.getSenha();
        String basicToken = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());

        UsuarioLoginResponse usuarioLoginResponse = new UsuarioLoginResponse();
        usuarioLoginResponse.setNome(usuario.getNome());
        usuarioLoginResponse.setEmail(usuario.getEmail());
        usuarioLoginResponse.setToken(basicToken);
        usuarioLoginResponse.setRole(usuario.getRole().name());

        return ResponseEntity.ok(usuarioLoginResponse);
    }
}
