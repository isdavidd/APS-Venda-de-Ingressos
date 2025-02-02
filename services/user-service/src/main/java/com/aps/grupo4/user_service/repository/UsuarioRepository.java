package com.aps.grupo4.user_service.repository;

import com.aps.grupo4.user_service.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByNome(String nome);

    Usuario findByCpf(String cpf);

    @Query("""
            UPDATE Usuario u
            SET u.nome = :nome,
            u.email = :email,
            u.senha = :senha,
            u.telefone = :telefone
            WHERE u.cpf = :cpf
            """)
    @Modifying
    int updateByCpf(@Param("nome") String nome,
                    @Param("email") String email,
                    @Param("telefone") String telefone,
                    @Param("senha") String senha,
                    @Param("cpf") String cpf);

    int deleteByCpf(String cpf);
}
