package com.directory.report_api.model.response;

import com.directory.report_api.model.enums.ContactInfoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContactInfoResponse {
    private UUID id;
    private ContactInfoType infoType;
    private String content;
}

