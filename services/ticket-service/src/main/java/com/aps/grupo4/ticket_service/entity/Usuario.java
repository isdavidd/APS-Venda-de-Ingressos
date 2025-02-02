package com.aps.grupo4.ticket_service.entity;

import com.aps.grupo4.ticket_service.entity.converter.Roles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Roles role;
}
