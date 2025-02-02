package com.aps.grupo4.payment_service.controller;

import com.aps.grupo4.payment_service.controller.dtos.CreatePaymentDTO;
import com.aps.grupo4.payment_service.entity.Payment;
import com.aps.grupo4.payment_service.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Pagamnento", description = "Endpoints para operações envolvendo pagamentos.")
@RequestMapping("/v1/payments")
public class PaymentController {
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/payment")
    @Operation(summary = "Efetuar pagamento", description = "Cadastra um pagamento no banco de dados.")
    public ResponseEntity<Object> createPayment(@RequestBody CreatePaymentDTO createPaymentDTO){
        try{
            Long paymentId = paymentService.createPayment(createPaymentDTO);
            return ResponseEntity.ok().body(paymentId);
        }
        catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    @GetMapping("/payment/{userId}")
    @Operation(summary = "Buscar pagamentos por ID de usuário", description = "Retorna todos os pagamentos efetuados pelo usuário com base no ID informado.")
    public ResponseEntity<Object> getPaymentsByUserId(@PathVariable("userId") Long userId){
        try {
            List<Payment> payment = paymentService.getPaymentsByUserId(userId);
            if (!payment.isEmpty())
                return ResponseEntity.ok().body(payment);
            else
                return ResponseHandler.responseBuilder(String.format("Não foram ecncontrados pagamentos realizados pelo usuário com id: %d.", userId), HttpStatus.NOT_FOUND, payment);
        } catch (RuntimeException e) {
            return ResponseHandler.responseBuilder(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
