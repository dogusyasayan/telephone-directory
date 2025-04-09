package com.directory.contact_api.rabbitmq.producer;

import com.directory.contact_api.rabbitmq.event.ContactCreatedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.UUID;

import static com.directory.contact_api.rabbitmq.config.RabbitMQConfig.CONTACT_CREATED_QUEUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContactRabbitProducerTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private ContactRabbitProducer contactRabbitProducer;

    @Test
    void it_should_send_contact_created_event_to_rabbitmq() {
        // given
        ContactCreatedEvent event = ContactCreatedEvent.builder()
                .contactId(UUID.randomUUID())
                .build();

        // when
        contactRabbitProducer.sendContactCreatedEvent(event);

        // then
        ArgumentCaptor<String> queueCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<Object> eventCaptor = ArgumentCaptor.forClass(Object.class);

        verify(rabbitTemplate, times(1)).convertAndSend(queueCaptor.capture(), eventCaptor.capture());

        assertThat(queueCaptor.getValue()).isEqualTo(CONTACT_CREATED_QUEUE);
        assertThat(eventCaptor.getValue()).isEqualTo(event);
    }
}
