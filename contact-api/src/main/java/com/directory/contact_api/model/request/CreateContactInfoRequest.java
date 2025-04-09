package com.directory.contact_api.model.request;

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
public class CreateContactInfoRequest {
    private UUID contactId;
    private ContactInfoType infoType;
    private String content;
}