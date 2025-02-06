package com.aps.grupo4.user_service.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;


@Schema(description = "Objeto para representar as informações retornadas ao autenticar um usuário")
public class UsuarioLoginResponse {
    @Schema(example = "nome legal")
    private String nome;
    @Schema(example = "19607112709")
    private String cpf;
    @Schema(example = "teste@gmail.com")
    private String email;
    @Schema(example = "21986607486")
    private String telefone;
    @Schema(example = "ADMIN")
    private String role;
    @Schema(example = "Basic MTk2MDcxMTI3MDk6dGVzdGUxMjM=")
    private String token;

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getRole() { return role; }
    public String getToken() { return token; }

    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setEmail(String email) { this.email = email; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setRole(String role) { this.role = role; }
    public void setToken(String token) { this.token = token; }
}
