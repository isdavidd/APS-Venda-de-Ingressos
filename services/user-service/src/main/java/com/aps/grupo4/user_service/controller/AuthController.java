package com.aps.grupo4.user_service.controller;

import com.aps.grupo4.user_service.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<String> generateBasicToken(@RequestParam String cpf, @RequestParam String senha) {

        var usuario = usuarioService.getUsuarioPorCpf(cpf);

        if (usuario == null) {
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
        }

        String auth = cpf + ":" + senha;
        String basicToken = "Basic " + Base64.getEncoder().encodeToString(auth.getBytes());

        return ResponseEntity.ok(basicToken);
    }
}
