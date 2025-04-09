package com.directory.contact_api.rabbitmq.producer;

import com.directory.contact_api.rabbitmq.event.ContactCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.directory.contact_api.rabbitmq.config.RabbitMQConfig.CONTACT_CREATED_QUEUE;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactRabbitProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendContactCreatedEvent(ContactCreatedEvent event) {
        log.info("Sending event to RabbitMQ: {}", event);
        rabbitTemplate.convertAndSend(CONTACT_CREATED_QUEUE, event);
    }
}