package com.aps.grupo4.payment_service.repository;


import com.aps.grupo4.payment_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    @Query(value = "SELECT * FROM pagamento p WHERE p.id_usuario = :usuarioId", nativeQuery = true)
    List<Payment> findPaymentsByUsuarioId(@Param("usuarioId") Long usuarioId);
}
