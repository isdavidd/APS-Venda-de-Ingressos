package com.aps.grupo4.user_service.controller;


import com.aps.grupo4.user_service.entity.Usuario;
import com.aps.grupo4.user_service.services.UsuarioService;
import com.aps.grupo4.user_service.validation.UsuarioNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UsusarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/users")
    public ResponseEntity<Object> BuscaUsuarios() {
        var usuarios = usuarioService.getUsuarios();

        if (usuarios.isEmpty()) {
            ResponseHandler.responseBuilder("Não há usuários", HttpStatus.NO_CONTENT, usuarios);
        }

        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> BuscaUsuarioPorId(@PathVariable("id") Long id) {
        try {
            var usuario = usuarioService.getUsuarioPorId(id);

            return ResponseEntity.ok().body(usuario);

        } catch (UsuarioNaoEncontradoException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> atualizaUsuario(@PathVariable("id") Long id) {
        return null;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deletaUsuario(@PathVariable("id") Long id) {
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registraUsuario(@RequestBody Usuario usuario) {
        try {
            var usuarioRegistrado = usuarioService.registerUsuario(usuario);

            return ResponseEntity.ok().body(usuarioRegistrado);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
