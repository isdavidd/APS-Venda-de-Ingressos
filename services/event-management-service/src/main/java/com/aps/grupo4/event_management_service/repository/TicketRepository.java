package com.aps.grupo4.event_management_service.repository;

import com.aps.grupo4.event_management_service.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Long countByEventoIdAndUsuarioIdIsNull(Long eventoId);
}
