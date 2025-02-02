package com.aps.grupo4.user_service.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {

    @Schema(example = "João da Silva")
    @NotBlank(message = "O nome de usuário deve ter pelo menos 255 caracteres")
    private String nome;

    @Schema(example = "joao.silva@email.com")
    @NotBlank(message = "O nome de usuário deve ter email")
    @Email(message = "O email deve ser válido")
    private String email;

    @Schema(example = "11999999999")
    @Column(name = "telefone")
    private String telefone;

    @Schema(example = "12345678901")
    @NotBlank(message = "O usuário deve ter um cpf")
    @CPF
    private String cpf;

    @Schema(example = "senhaForte123")
    @NotBlank(message = "O usuário precisa ter senha")
    private String senha;
}
