package com.directory.contact_api.model.response;

import com.directory.contact_api.domain.enums.ContactInfoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoResponse {
    private UUID id;
    private ContactInfoType infoType;
    private String content;
}