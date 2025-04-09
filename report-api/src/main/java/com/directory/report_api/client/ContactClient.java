package com.directory.report_api.client;


import com.directory.report_api.model.response.ContactDetailResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactClient {

    private final RestTemplate restTemplate;

    public ContactDetailResponse getContactDetail(UUID contactId) {
        String url = "http://localhost:8080/contacts/" + contactId;
        return restTemplate.getForObject(url, ContactDetailResponse.class);
    }
}
