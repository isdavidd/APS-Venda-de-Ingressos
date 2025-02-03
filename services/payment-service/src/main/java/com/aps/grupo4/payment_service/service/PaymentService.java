package com.aps.grupo4.payment_service.service;

import com.aps.grupo4.payment_service.controller.dtos.CreatePaymentDTO;
import com.aps.grupo4.payment_service.controller.dtos.UserDTO;
import com.aps.grupo4.payment_service.entity.Payment;
import com.aps.grupo4.payment_service.repository.PaymentRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    private PaymentRepository paymentRepository;
    private JavaMailSender mailSender;
    private RestTemplate restTemplate;

    public PaymentService(PaymentRepository paymentRepository, JavaMailSender mailSender, RestTemplate restTemplate) {
        this.paymentRepository = paymentRepository;
        this.mailSender = mailSender;
        this.restTemplate = restTemplate;
    }

    public Long createPayment(CreatePaymentDTO createPaymentDTO){
        var entity = new Payment(
                createPaymentDTO.getId(),
                createPaymentDTO.getUsuarioId(),
                createPaymentDTO.getValor(),
                createPaymentDTO.getMetodoPagamento(),
                createPaymentDTO.getEmailEnviado(),
                createPaymentDTO.getDataPagamento()
        );

        Payment paymentSaved = paymentRepository.save(entity);
        try {
            enviarEmailConfirmacaoDeCompra(entity.getValor(), entity.getUsuarioId());
            atualizarStatusEmail(entity);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        enviarNotificacaoCompra();
        return paymentSaved.getId();
    }

    private void atualizarStatusEmail(Payment entity){
        Optional<Payment> paymentTemp = paymentRepository.findById(entity.getId());
        if(paymentTemp.isPresent()){
            var payment = paymentTemp.get();
            payment.setEmailEnviado(true);
            paymentRepository.save(payment);
        }
    }

    private void enviarNotificacaoCompra() {
        String url = "http://notifications-service:8082/notify";
        String mensagem = "Seu ingresso foi comprado com sucesso!";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN); // Define o Content-Type correto

        HttpEntity<String> entity = new HttpEntity<>(mensagem, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
            System.out.println("Notificação de compra enviada com sucesso. Status: " + response.getStatusCode());
        } catch (ResourceAccessException e) {
            System.out.println("Serviço de notificação indisponível. Notificação não enviada. " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getPaymentsByUserId(Long userId){
        return paymentRepository.findPaymentsByUsuarioId(userId);
    }

    public void enviarEmailConfirmacaoDeCompra(BigDecimal valor, Long getUsuarioId) throws MessagingException {

        MimeMessage mail = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        String mensagem = "Seu pagamento no valor de R$ " + valor + " foi realizado com sucesso!";
        try {
            helper = new MimeMessageHelper(mail, true);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        String email = BuscarEmailUsuario(getUsuarioId);

        helper.setTo(email);
        helper.setSubject("Confirmação de Pagamento");
        helper.setText(mensagem, true); // "true" indica que pode ser HTML

        mailSender.send(mail);

        System.out.print("E-mail enviado com sucesso para " + email + ".");
    }

    public String BuscarEmailUsuario(Long usuarioId){
        String url = "http://user-service:8084/user-service/user/id/" + usuarioId;
        var temp = restTemplate.getForObject(url, UserDTO.class);
        return temp.getEmail();
    }
}
