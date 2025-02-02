package com.aps.grupo4.event_management_service.config;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
public class RabbitConfig {

    private static final String EXCHANGE_NAME = "evento_exchange";

    @Bean
    public DirectExchange eventoExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue eventoCriadoQueue() {
        return new Queue("evento_criado", true);
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

    // Bindings (conectam filas à exchange com routing keys)
    @Bean
    public Binding bindingEventoCriado(Queue eventoCriadoQueue, DirectExchange eventoExchange) {
        return BindingBuilder.bind(eventoCriadoQueue)
                .to(eventoExchange)
                .with("evento.criado");
    }

    @Bean
    public Binding bindingCapacidadeAumentada(Queue capacidadeEventoAumentadaQueue, DirectExchange eventoExchange) {
        return BindingBuilder.bind(capacidadeEventoAumentadaQueue)
                .to(eventoExchange)
                .with("evento.capacidade.aumentada");
    }

    @Bean
    public Binding bindingCapacidadeReduzida(Queue capacidadeEventoReduzidaQueue, DirectExchange eventoExchange) {
        return BindingBuilder.bind(capacidadeEventoReduzidaQueue)
                .to(eventoExchange)
                .with("evento.capacidade.reduzida");
    }

    @Bean
    public Binding bindingEventoCancelado(Queue eventoCanceladoQueue, DirectExchange eventoExchange) {
        return BindingBuilder.bind(eventoCanceladoQueue)
                .to(eventoExchange)
                .with("evento.cancelado");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }
}
