package com.aps.grupo4.payment_service.service;

import com.aps.grupo4.payment_service.controller.dtos.CreatePaymentDTO;
import com.aps.grupo4.payment_service.entity.Payment;
import com.aps.grupo4.payment_service.repository.PaymentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private JavaMailSender mailSender;

    public PaymentService(PaymentRepository paymentRepository, JavaMailSender mailSender) {
        this.paymentRepository = paymentRepository;
        this.mailSender = mailSender;
    }

    public Long createPayment(CreatePaymentDTO createPaymentDTO){
        var entity = Payment.builder()
                .id(createPaymentDTO.getId())
                .usuarioId(createPaymentDTO.getUsuarioId())
                .valor(createPaymentDTO.getValor())
                .metodoPagamento(createPaymentDTO.getMetodoPagamento())
                .emailEnviado(createPaymentDTO.getEmailEnviado())
                .dataPagamento(createPaymentDTO.getDataPagamento())
                .build();

        Payment paymentSaved = paymentRepository.save(entity);
        try {
            sendPaymentConfirmationEmail("danielpedro7676@gmail.com", entity.getValor());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return paymentSaved.getId();
    }

    public List<Payment> getPaymentsByUserId(Long userId){
        return paymentRepository.findPaymentsByUsuarioId(userId);
    }

    public void sendPaymentConfirmationEmail(String email, BigDecimal valor) throws MessagingException {

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        String mensagem = "Seu pagamento no valor de R$ " + valor + " foi realizado com sucesso!";
        try {
            helper = new MimeMessageHelper(mail, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        helper.setTo(email);
        helper.setSubject("Confirmação de Pagamento");
        helper.setText(mensagem, true); // "true" indica que pode ser HTML

        mailSender.send(mail);
    }
}
