package com.aps.grupo4.notification_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "notificacao")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_usuario")
    @NotNull(message = "Uma notificação deve estar associada a um usuário.")
    private Long usuarioId;

    @Column(name = "mensagem")
    @NotBlank(message = "Uma notificação precisa possuir uma mensagem.")
    private String message;

    public Notification(Long id, Long usuarioId, String message) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public @NotBlank String getMessage() {
        return message;
    }

    public void setMessage(@NotBlank String message) {
        this.message = message;
    }
}
