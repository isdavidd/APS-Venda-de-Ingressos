package com.aps.grupo4.event_management_service.repository;


import com.aps.grupo4.event_management_service.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {

    Optional<Evento> findByNomeEventoIgnoreCase(String nomeEvento);

    List<Evento> findByDataEvento(LocalDateTime dataEvento);

    List<Evento> findByDataEventoAfter(LocalDateTime dataMinima);

    List<Evento> findByDataEventoBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Evento> findByCapacidadeEventoGreaterThanEqual(Integer capacidadeMinima);

    List<Evento> findByCapacidadeEventoLessThanEqual(Integer capacidadeMaxima);

    List<Evento> findByLocalEventoIgnoreCase(String local);

    List<Evento> findByNomeEventoStartingWithIgnoreCase(String nomeEvento);

    int deleteByNomeEvento(String nomeEvento);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Evento e SET " +
            "e.nomeEvento = :nomeEvento, " +
            "e.dataEvento = :dataEvento, " +
            "e.localEvento = :localEvento, " +
            "e.valorIngressoEvento = :valorIngressoEvento, " +
            "e.capacidadeEvento = :capacidadeEvento, " +
            "e.descricaoEvento = :descricaoEvento " +
            "WHERE e.id = :id")
    int updateEventoById(
            @Param("id") Integer id,
            @Param("nomeEvento") String nomeEvento,
            @Param("dataEvento") LocalDateTime dataEvento,
            @Param("localEvento") String localEvento,
            @Param("valorIngressoEvento") BigDecimal valorIngressoEvento,
            @Param("capacidadeEvento") Integer capacidadeEvento,
            @Param("descricaoEvento") String descricaoEvento);

}
