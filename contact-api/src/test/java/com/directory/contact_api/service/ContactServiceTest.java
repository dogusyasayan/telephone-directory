package com.directory.contact_api.service;

import com.directory.contact_api.builder.ContactBuilder;
import com.directory.contact_api.converter.ContactConverter;
import com.directory.contact_api.domain.Contact;
import com.directory.contact_api.exception.ContactNotFoundException;
import com.directory.contact_api.exception.enums.ErrorStatus;
import com.directory.contact_api.rabbitmq.event.ContactCreatedEvent;
import com.directory.contact_api.rabbitmq.producer.ContactRabbitProducer;
import com.directory.contact_api.model.request.CreateContactRequest;
import com.directory.contact_api.model.response.ContactDetailResponse;
import com.directory.contact_api.model.response.ContactResponse;
import com.directory.contact_api.repository.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ContactServiceTest {

    @InjectMocks
    private ContactService contactService;

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactBuilder contactBuilder;

    @Mock
    private ContactConverter contactConverter;

    @Mock
    private ContactRabbitProducer contactRabbitProducer;

    @Test
    void it_should_create_contact_and_send_event() {
        // given
        CreateContactRequest request = CreateContactRequest.builder()
                .name("Doğuş")
                .surname("Yaşayan")
                .company("Masthub")
                .build();

        Contact contact = Contact.builder().id(UUID.randomUUID()).build();
        ContactCreatedEvent event = ContactCreatedEvent.builder().contactId(contact.getId()).build();

        given(contactBuilder.build(request)).willReturn(contact);
        given(contactBuilder.build(contact)).willReturn(event);

        // when
        String result = contactService.createContact(request);

        // then
        verify(contactRepository).save(contact);
        verify(contactRabbitProducer).sendContactCreatedEvent(event);
        assertThat(result).isEqualTo("Contact successfully created.");
    }

    @Test
    void it_should_delete_contact_if_exists() {
        // given
        UUID contactId = UUID.randomUUID();
        Contact contact = Contact.builder().id(contactId).build();

        given(contactRepository.findById(contactId)).willReturn(Optional.of(contact));

        // when
        String result = contactService.deleteContact(contactId);

        // then
        verify(contactRepository).delete(contact);
        assertThat(result).isEqualTo("Contact successfully deleted.");
    }

    @Test
    void it_should_throw_exception_when_contact_not_found_on_delete() {
        // given
        UUID contactId = UUID.randomUUID();
        given(contactRepository.findById(contactId)).willReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() -> contactService.deleteContact(contactId));

        // then
        assertThat(thrown).isInstanceOf(ContactNotFoundException.class);
    }

    @Test
    void it_should_return_all_contacts() {
        // given
        List<Contact> contacts = List.of(
                Contact.builder().id(UUID.randomUUID()).build()
        );
        List<ContactResponse> responseList = List.of(
                ContactResponse.builder().build()
        );

        given(contactRepository.findAll()).willReturn(contacts);
        given(contactConverter.convertToResponseList(contacts)).willReturn(responseList);

        // when
        List<ContactResponse> result = contactService.getAllContacts();

        // then
        assertThat(result).isEqualTo(responseList);
    }

    @Test
    void it_should_return_contact_detail() {
        // given
        UUID contactId = UUID.randomUUID();
        Contact contact = Contact.builder().id(contactId).build();
        ContactDetailResponse detailResponse = ContactDetailResponse.builder().build();

        given(contactRepository.findById(contactId)).willReturn(Optional.of(contact));
        given(contactConverter.convertToDetail(contact)).willReturn(detailResponse);

        // when
        ContactDetailResponse result = contactService.getContactDetail(contactId);

        // then
        assertThat(result).isEqualTo(detailResponse);
    }

    @Test
    void it_should_throw_exception_when_contact_not_found_on_get_detail() {
        // given
        UUID contactId = UUID.randomUUID();
        given(contactRepository.findById(contactId)).willReturn(Optional.empty());

        // when
        Throwable thrown = catchThrowable(() -> contactService.getContactDetail(contactId));

        // then
        assertThat(thrown).isInstanceOf(ContactNotFoundException.class)
                .hasMessage(ErrorStatus.CONTACT_NOT_FOUND.name());
    }
}
