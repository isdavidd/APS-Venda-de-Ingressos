package com.aps.grupo4.ticket_service.repository;

import com.aps.grupo4.ticket_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    void deleteByEventoId(Long idEvento);

    @Query("""
            
            """)
    int registrosAfetados


}
