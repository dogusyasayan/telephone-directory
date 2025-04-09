package com.directory.contact_api.controller;

import com.directory.contact_api.model.request.CreateContactRequest;
import com.directory.contact_api.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void it_should_create_contact() throws Exception {
        // given
        CreateContactRequest request = CreateContactRequest.builder()
                .name("Doğuş")
                .surname("Yaşayan")
                .company("Masthub")
                .build();

        // when
        ResultActions result = mockMvc.perform(post("/contacts/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        result.andExpect(status().isCreated());
        ArgumentCaptor<CreateContactRequest> captor = ArgumentCaptor.forClass(CreateContactRequest.class);
        verify(contactService).createContact(captor.capture());
        assertThat(captor.getValue().getName()).isEqualTo("Doğuş");
    }

    @Test
    void it_should_delete_contact() throws Exception {
        // given
        UUID contactId = UUID.randomUUID();

        // when
        ResultActions result = mockMvc.perform(delete("/contacts/delete/" + contactId));

        // then
        result.andExpect(status().isOk());
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(contactService).deleteContact(captor.capture());
        assertThat(captor.getValue()).isEqualTo(contactId);
    }

    @Test
    void it_should_get_all_contacts() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/contacts"));

        // then
        result.andExpect(status().isOk());
        verify(contactService).getAllContacts();
    }

    @Test
    void it_should_get_contact_detail() throws Exception {
        // given
        UUID contactId = UUID.randomUUID();

        // when
        ResultActions result = mockMvc.perform(get("/contacts/" + contactId));

        // then
        result.andExpect(status().isOk());
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(contactService).getContactDetail(captor.capture());
        assertThat(captor.getValue()).isEqualTo(contactId);
    }
}
