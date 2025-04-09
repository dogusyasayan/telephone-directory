package com.directory.contact_api.builder;

import com.directory.contact_api.domain.Contact;
import com.directory.contact_api.rabbitmq.event.ContactCreatedEvent;
import com.directory.contact_api.model.request.CreateContactRequest;
import org.springframework.stereotype.Component;

@Component
public class ContactBuilder {


    public Contact build(CreateContactRequest request) {
        return Contact.builder()
                .firstName(request.getName())
                .lastName(request.getSurname())
                .company(request.getCompany())
                .phone(request.getPhone())
                .email(request.getEmail())
                .location(request.getLocation())
                .build();
    }

    public ContactCreatedEvent build(Contact contact) {
        return ContactCreatedEvent.builder()
                .contactId(contact.getId())
                .build();
    }
}
