package com.directory.contact_api.converter;

import com.directory.contact_api.domain.Contact;
import com.directory.contact_api.model.response.ContactDetailResponse;
import com.directory.contact_api.model.response.ContactResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ContactConverterTest {

    @InjectMocks
    private ContactConverter contactConverter;

    @Test
    void it_should_convert_to_contact_response() {
        // given
        Contact contact = Contact.builder()
                .id(UUID.randomUUID())
                .firstName("Doğuş")
                .lastName("Yaşayan")
                .company("Masthub")
                .build();

        // when
        ContactResponse response = contactConverter.convertToResponse(contact);

        // then
        assertThat(response.getId()).isEqualTo(contact.getId());
        assertThat(response.getFirstName()).isEqualTo("Doğuş");
        assertThat(response.getLastName()).isEqualTo("Yaşayan");
        assertThat(response.getCompany()).isEqualTo("Masthub");
    }

    @Test
    void it_should_convert_to_contact_response_list() {
        // given
        Contact contact1 = Contact.builder()
                .id(UUID.randomUUID())
                .firstName("Ali")
                .lastName("Yılmaz")
                .company("CompanyA")
                .build();

        Contact contact2 = Contact.builder()
                .id(UUID.randomUUID())
                .firstName("Ayşe")
                .lastName("Kaya")
                .company("CompanyB")
                .build();

        List<Contact> contacts = List.of(contact1, contact2);

        // when
        List<ContactResponse> responses = contactConverter.convertToResponseList(contacts);

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getFirstName()).isEqualTo("Ali");
        assertThat(responses.get(1).getFirstName()).isEqualTo("Ayşe");
    }

    @Test
    void it_should_convert_to_contact_detail_response() {
        // given
        UUID contactId = UUID.randomUUID();
        Contact contact = Contact.builder()
                .id(contactId)
                .firstName("Doğuş")
                .lastName("Yaşayan")
                .company("Masthub")
                .phone("555-123-4567")
                .email("dogus@example.com")
                .location("İstanbul")
                .build();

        // when
        ContactDetailResponse response = contactConverter.convertToDetail(contact);

        // then
        assertThat(response.getId()).isEqualTo(contactId);
        assertThat(response.getName()).isEqualTo("Doğuş");
        assertThat(response.getSurname()).isEqualTo("Yaşayan");
        assertThat(response.getCompany()).isEqualTo("Masthub");
        assertThat(response.getPhone()).isEqualTo("555-123-4567");
        assertThat(response.getEmail()).isEqualTo("dogus@example.com");
        assertThat(response.getLocation()).isEqualTo("İstanbul");
    }
}
