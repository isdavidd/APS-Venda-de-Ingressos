package com.aps.grupo4.ticket_service.repository;

import com.aps.grupo4.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    void deleteByEventoId(Long idEvento);

    @Modifying
    @Query(value = """
            DELETE FROM Ticket t
            WHERE t.id_evento = :eventoId
            AND t.id_usuario IS NULL
            LIMIT :quantidadeRegistros;
            """, nativeQuery = true)
    Integer registrosAfetados(@Param("eventoId") Long eventoId, @Param("quantidadeRegistros") int quantidadeRegistros);
}
