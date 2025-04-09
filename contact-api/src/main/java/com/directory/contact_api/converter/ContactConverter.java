package com.directory.contact_api.converter;

import com.directory.contact_api.domain.Contact;
import com.directory.contact_api.model.response.ContactDetailResponse;
import com.directory.contact_api.model.response.ContactResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactConverter {

    public ContactResponse convertToResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .company(contact.getCompany())
                .build();
    }

    public List<ContactResponse> convertToResponseList(List<Contact> contacts) {
        return contacts.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public ContactDetailResponse convertToDetail(Contact contact) {
        return ContactDetailResponse.builder()
                .id(contact.getId())
                .name(contact.getFirstName())
                .surname(contact.getLastName())
                .company(contact.getCompany())
                .phone(contact.getPhone())
                .email(contact.getEmail())
                .location(contact.getLocation())
                .build();
    }
}
