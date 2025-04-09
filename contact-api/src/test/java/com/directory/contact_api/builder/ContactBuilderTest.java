package com.directory.contact_api.builder;

import com.directory.contact_api.domain.Contact;
import com.directory.contact_api.rabbitmq.event.ContactCreatedEvent;
import com.directory.contact_api.model.request.CreateContactRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ContactBuilderTest {

    @InjectMocks
    private ContactBuilder contactBuilder;

    @Test
    void it_should_build_contact() {
        // given
        CreateContactRequest request = CreateContactRequest.builder()
                .name("dogus")
                .surname("yasayan")
                .company("masthub")
                .build();

        // when
        Contact contact = contactBuilder.build(request);

        // then
        assertThat(contact.getFirstName()).isEqualTo("dogus");
        assertThat(contact.getLastName()).isEqualTo("yasayan");
        assertThat(contact.getCompany()).isEqualTo("masthub");
    }

    @Test
    void it_should_build_contact_created_event() {
        // given
        UUID contactId = UUID.randomUUID();
        Contact contact = Contact.builder()
                .id(contactId)
                .build();

        // when
        ContactCreatedEvent event = contactBuilder.build(contact);

        // then
        assertThat(event.getContactId()).isEqualTo(contactId);
    }
}
