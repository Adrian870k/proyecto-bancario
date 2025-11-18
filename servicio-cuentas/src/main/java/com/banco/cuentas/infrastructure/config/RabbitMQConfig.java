package com.banco.cuentas.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de RabbitMQ para el servicio de cuentas
 */
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name:banco_eventos_exchange}")
    private String topicExchangeName;
    @Value("${rabbitmq.queue.name:cuenta_cliente_eventos_queue}")
    private String queueName;
    @Value("${rabbitmq.routing.key.cliente:cliente.evento}")
    private String clienteRoutingKey;


    @Bean
    public Queue queue() {
        return new Queue(queueName, true);
    }


    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(topicExchange())
                .with(clienteRoutingKey);
    }
}
