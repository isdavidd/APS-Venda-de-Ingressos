package com.aps.grupo4.user_service.controller;

import com.aps.grupo4.user_service.entity.dto.UsuarioDTO;
import com.aps.grupo4.user_service.entity.dto.UsuarioLoginRequest;
import com.aps.grupo4.user_service.entity.dto.UsuarioLoginResponse;
import com.aps.grupo4.user_service.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Endpoints para autenticação de usuários")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Retornar informações de usuário", description = "Retorna informações de usuário cadastrado e também o token de autenticação a partir do seu cpf e senha")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Informações retornadas com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioLoginResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")

    })
    public ResponseEntity<Object> generateBasicToken(
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON contendo as credenciais de um usuário",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                        {
                            "cpf": "12345678901",
                            "senha": "senhaForte123"
                        }
                        """)
                    )
            ) @RequestBody UsuarioLoginRequest request) {

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
