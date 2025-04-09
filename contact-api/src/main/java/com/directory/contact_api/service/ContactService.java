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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final ContactBuilder contactBuilder;
    private final ContactConverter contactConverter;
    private final ContactRabbitProducer contactRabbitProducer;

    @Transactional
    public String createContact(CreateContactRequest request) {
        Contact contact = contactBuilder.build(request);
        contactRepository.save(contact);
        ContactCreatedEvent contactCreatedEvent = contactBuilder.build(contact);
        contactRabbitProducer.sendContactCreatedEvent(contactCreatedEvent);
        return "Contact successfully created.";
    }

    @Transactional
    public String deleteContact(UUID contactId) {
        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isEmpty()) {
            throw new ContactNotFoundException(ErrorStatus.CONTACT_NOT_FOUND);
        }
        contactRepository.delete(optionalContact.get());
        return "Contact successfully deleted.";
    }

    @Transactional(readOnly = true)
    public List<ContactResponse> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contactConverter.convertToResponseList(contacts);
    }

    public ContactDetailResponse getContactDetail(UUID contactId) {
        Contact contact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ContactNotFoundException(ErrorStatus.CONTACT_NOT_FOUND));
        return contactConverter.convertToDetail(contact);
    }
}