package com.aps.grupo4.user_service.entity.dto;

public class UsuarioLoginResponse {
    private String nome;
    private String email;
    private String role;
    private String token;

    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getToken() { return token; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setToken(String token) { this.token = token; }
}
