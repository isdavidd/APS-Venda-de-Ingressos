package com.aps.grupo4.event_management_service.repository;

import com.aps.grupo4.event_management_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNome(String nome);
}
