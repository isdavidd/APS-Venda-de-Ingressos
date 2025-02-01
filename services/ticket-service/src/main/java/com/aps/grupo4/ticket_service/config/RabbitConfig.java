package com.aps.grupo4.ticket_service.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue eventoCriadoQueue() {
        return new Queue("evento_criado", true);  // A fila será persistente
    }

    @Bean
    public Queue capacidadeEventoAumentadaQueue() {
        return new Queue("capacidade_aumentada", true);
    }

    @Bean
    public Queue capacidadeEventoReduzidaQueue() {
        return new Queue("capacidade_reduzida", true);
    }

    @Bean
    public Queue eventoCanceladoQueue() {
        return new Queue("evento_cancelado", true);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
