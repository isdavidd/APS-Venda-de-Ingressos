package com.aps.grupo4.event_management_service.repository;


import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface EventoRepository extends JpaRepository<Evento, Long>, EventoRepositoryCustom  {

    Optional<Evento> findByNomeEventoIgnoreCase(String nomeEvento);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
            UPDATE Evento e SET 
            e.nomeEvento = :nomeEvento, 
            e.dataEvento = :dataEvento, 
            e.localEvento = :localEvento, 
            e.valorIngressoEvento = :valorIngressoEvento, 
            e.capacidadeEvento = :capacidadeEvento, 
            e.descricaoEvento = :descricaoEvento, 
            e.ufEvento = :ufEvento 
            WHERE e.id = :id
            """)
    int updateEventoById(
            @Param("id") Long id,
            @Param("nomeEvento") String nomeEvento,
            @Param("dataEvento") LocalDateTime dataEvento,
            @Param("localEvento") String localEvento,
            @Param("valorIngressoEvento") BigDecimal valorIngressoEvento,
            @Param("capacidadeEvento") Integer capacidadeEvento,
            @Param("descricaoEvento") String descricaoEvento,
            @Param("ufEvento") UFEnum ufEvento);

}
