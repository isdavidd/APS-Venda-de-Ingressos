package com.aps.grupo4.notification_service.controller;

public class NotificationDTO {
    private String id;
    private String titulo;
    private String descricao;
    public NotificationDTO() {}

    public NotificationDTO(String id, String titulo, String descricao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}