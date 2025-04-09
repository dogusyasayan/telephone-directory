package com.directory.contact_api.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetailResponse {
    private UUID id;
    private String name;
    private String surname;
    private String company;
    private String phone;
    private String email;
    private String location;
}
