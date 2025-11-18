package com.banco.clientes.infrastructure.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de RabbitMQ para el servicio de clientes.
 */
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.exchange.name:banco_eventos_exchange}")
    private String topicExchangeName;

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(topicExchangeName);
    }
}
