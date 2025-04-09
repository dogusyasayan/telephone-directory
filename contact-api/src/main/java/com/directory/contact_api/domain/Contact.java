package com.directory.contact_api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;

    private String lastName;

    private String company;

    private String phone;

    private String email;

    private String location;
}
