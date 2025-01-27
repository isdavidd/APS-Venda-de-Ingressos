package com.aps.grupo4.event_management_service.repository;


import com.aps.grupo4.event_management_service.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    Optional<Evento> findByNomeEvento(String nomeEvento);

    void deleteByNomeEvento(String nomeEvento);

}
