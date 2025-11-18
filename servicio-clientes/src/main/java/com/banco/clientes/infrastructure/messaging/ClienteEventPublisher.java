package com.banco.clientes.infrastructure.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Publicador de eventos de cliente.
 */
@Service
public class ClienteEventPublisher {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteEventPublisher.class);

    private final RabbitTemplate rabbitTemplate;

    @Value("${rabbitmq.exchange.name:banco_eventos_exchange}")
    private String topicExchangeName;

    @Value("${rabbitmq.routing.key.cliente:cliente.evento}")
    private String clienteRoutingKey;

    public ClienteEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Publica un mensaje de evento de cliente.
     */
    public void publicarEventoCliente(String mensaje) {
        try {
            rabbitTemplate.convertAndSend(topicExchangeName, clienteRoutingKey, mensaje);
            LOGGER.info("Evento de cliente publicado en RabbitMQ. RoutingKey: {}, Mensaje: {}",
                    clienteRoutingKey, mensaje);
        } catch (Exception e) {
            LOGGER.error("Error al publicar evento de cliente en RabbitMQ: {}", e.getMessage(), e);
        }
    }
}
