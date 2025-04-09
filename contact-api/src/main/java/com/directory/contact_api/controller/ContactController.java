package com.directory.contact_api.controller;


import com.directory.contact_api.model.request.CreateContactRequest;
import com.directory.contact_api.model.response.ContactDetailResponse;
import com.directory.contact_api.model.response.ContactResponse;
import com.directory.contact_api.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createContact(@RequestBody CreateContactRequest request) {
        return contactService.createContact(request);
    }

    @DeleteMapping("/delete/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteContact(@PathVariable UUID contactId) {
        return contactService.deleteContact(contactId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ContactResponse> getAllContacts() {
        return contactService.getAllContacts();
    }

    @GetMapping("/{contactId}")
    @ResponseStatus(HttpStatus.OK)
    public ContactDetailResponse getContactDetail(@PathVariable UUID contactId) {
        return contactService.getContactDetail(contactId);
    }
}
