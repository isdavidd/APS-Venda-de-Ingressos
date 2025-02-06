package com.aps.grupo4.user_service.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto para representar as credenciais de um usuário")
public class UsuarioLoginRequest {

    @Schema(example = "12345678901")
    private String cpf;
    @Schema(example = "senhaForte123")
    private String senha;

    public String getCpf() { return cpf; }
    public String getSenha() { return senha; }
}

