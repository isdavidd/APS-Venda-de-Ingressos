package com.aps.grupo4.ticket_service.repository;


import com.aps.grupo4.ticket_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByCpf(String cpf);

    Usuario findByNome(String nome);
}
