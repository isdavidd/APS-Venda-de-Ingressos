package com.aps.grupo4.user_service.repository;

import com.aps.grupo4.user_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);
}
