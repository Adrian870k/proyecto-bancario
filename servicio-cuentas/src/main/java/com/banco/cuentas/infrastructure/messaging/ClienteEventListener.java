package com.banco.cuentas.infrastructure.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class ClienteEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClienteEventListener.class);

    @RabbitListener(queues = "${rabbitmq.queue.name:cuenta_cliente_eventos_queue}")
    public void handleClienteEvent(String mensaje) {

        LOGGER.info("Evento de cliente recibido desde RabbitMQ: {}", mensaje);
    }
}
