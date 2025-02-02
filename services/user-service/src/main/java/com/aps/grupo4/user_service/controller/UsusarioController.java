package com.aps.grupo4.user_service.controller;


import com.aps.grupo4.user_service.entity.dto.UsuarioDTO;
import com.aps.grupo4.user_service.services.UsuarioService;
import com.aps.grupo4.user_service.validation.UsuarioNaoEncontradoException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-service")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsusarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/users")
    @Operation(summary = "Listar usuários", description = "Retorna a lista de usuários cadastrados")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado")
    })
    public ResponseEntity<Object> BuscaUsuarios() {
        try {
            var usuarios = usuarioService.getUsuarios();

            if (usuarios.isEmpty()) {
                ResponseHandler.responseBuilder("Não há usuários", HttpStatus.NOT_FOUND, usuarios);
            }

            return ResponseEntity.ok().body(usuarios);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/user/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário cadastrado a partir do seu ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")

    })
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

    @GetMapping("/user/{cpf}")
    @Operation(summary = "Buscar usuário por CPF", description = "Retorna um usuário cadastrado a partir do seu CPF")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> BuscaUsuarioPorCpf(
            @Parameter(description = "CPF do usuário a ser removido", example = "12345678901")
            @PathVariable("cpf") @CPF String cpf) {
        try {
            var usuario = usuarioService.getUsuarioPorCpf(cpf);

            return ResponseEntity.ok().body(usuario);

        } catch (UsuarioNaoEncontradoException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @DeleteMapping("/user/{cpf}")
    @Operation(summary = "Deletar usuário por CPF", description = "Remove um usuário com base no CPF informado")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> deletaUsuario(
            @Parameter(description = "CPF do usuário a ser removido", example = "12345678901")
            @PathVariable("cpf") @CPF String cpf)
    {
        try {
            var usuarioDeletado = usuarioService.deleteUsuario(cpf);

            return ResponseHandler.responseBuilder(usuarioDeletado, HttpStatus.NO_CONTENT, null);

        } catch (UsuarioNaoEncontradoException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.NOT_FOUND, null);


        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @PutMapping("/user")
    @Operation(summary = "Atualizar usuário por CPF", description = "Atualiza o nome, email e senha de um usuário com base no CPF informado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos campos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> atualizaUsuario(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON para criar um usuário",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                        {
                            "nome": "João da Silva",
                            "email": "joao.silva@email.com",
                            "telefone": "11999999999",
                        }
                        """)
                    )
            )
            UsuarioDTO usuarioDTO) {

        try {
            var usuarioCadastrado = usuarioService.updateUsuario(usuarioDTO);

            return ResponseHandler.responseBuilder(usuarioCadastrado, HttpStatus.OK, null);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }

    }

    @PostMapping("/register")
    @Operation(summary = "Criar usuário novo", description = "Cria um usuário novo com as informações passadas no Json")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = UsuarioDTO.class))),
            @ApiResponse(responseCode = "400", description = "Erro na validação dos campos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Object> registraUsuario(
            @RequestBody
            @Valid
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Objeto JSON para criar um usuário",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            examples = @ExampleObject(
                                    value = """
                        {
                            "nome": "João da Silva",
                            "email": "joao.silva@email.com",
                            "telefone": "11999999999",
                            "cpf": "12345678901",
                            "senha": "senhaForte123"
                        }
                        """)
                    )
            ) UsuarioDTO usuarioDTO) {
        try {
            var usuarioRegistrado = usuarioService.registerUsuario(usuarioDTO);

            return ResponseEntity.ok().body(usuarioRegistrado);

        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
