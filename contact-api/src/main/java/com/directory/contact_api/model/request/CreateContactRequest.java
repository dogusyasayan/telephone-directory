package com.directory.contact_api.model.request;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateContactRequest {
    private String name;
    private String surname;
    private String company;
    private String phone;
    private String email;
    private String location;
}
