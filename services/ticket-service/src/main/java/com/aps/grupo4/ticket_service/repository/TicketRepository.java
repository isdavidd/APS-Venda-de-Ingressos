package com.aps.grupo4.ticket_service.repository;

import com.aps.grupo4.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    @Modifying
    void deleteByEventoId(Long idEvento);

    @Modifying
    @Query(value = """
            DELETE FROM ingresso i
            WHERE i.id_evento = :eventoId
            AND i.id_usuario IS NULL
            LIMIT :quantidadeRegistros
            """, nativeQuery = true)
    void registrosAfetados(@Param("eventoId") Long eventoId, @Param("quantidadeRegistros") int quantidadeRegistros);

    @Query(value = "SELECT * FROM ingresso i WHERE i.id_usuario = :usuarioId", nativeQuery = true)
    List<Ticket> findTicketsByUserId(@Param("usuarioId") Long usuarioId);
}
