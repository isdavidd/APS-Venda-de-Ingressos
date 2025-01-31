package com.aps.grupo4.event_management_service.repository;

import com.aps.grupo4.event_management_service.entity.Evento;
import com.aps.grupo4.event_management_service.entity.converter.UFEnum;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EventoRepositoryCustomImpl implements EventoRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Evento> buscarEventosPorParametros(
            LocalDateTime dataInicio,
            LocalDateTime dataFim,
            String siglaUF,
            Integer capacidadeMinima,
            Integer capacidadeMaxima,
            String local,
            String nomeEvento
    ) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Evento> cq = cb.createQuery(Evento.class);
        Root<Evento> evento = cq.from(Evento.class);

        List<Predicate> predicates = new ArrayList<>();

        if (dataInicio != null) {
            predicates.add(cb.greaterThanOrEqualTo(evento.get("dataEvento"), dataInicio));
        }
        if (dataFim != null) {
            predicates.add(cb.lessThanOrEqualTo(evento.get("dataEvento"), dataFim));
        }
        if (siglaUF != null) {
            predicates.add(cb.equal(evento.get("ufEvento"), siglaUF));
        }
        if (capacidadeMinima != null) {
            predicates.add(cb.greaterThanOrEqualTo(evento.get("capacidadeEvento"), capacidadeMinima));
        }
        if (capacidadeMaxima != null) {
            predicates.add(cb.lessThanOrEqualTo(evento.get("capacidadeEvento"), capacidadeMaxima));
        }
        if (local != null && !local.isBlank()) {
            predicates.add(cb.like(cb.lower(evento.get("localEvento")), "%" + local.toLowerCase() + "%"));
        }
        if (nomeEvento != null && !nomeEvento.isBlank()) {
            predicates.add(cb.like(cb.lower(evento.get("nomeEvento")), nomeEvento.toLowerCase() + "%"));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Evento> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}

