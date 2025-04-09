package com.directory.contact_api.exception.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorStatus {
    CONTACT_NOT_FOUND("CNT001", HttpStatus.NOT_FOUND),
    CONTACT_ALREADY_EXISTS("CNT002", HttpStatus.CONFLICT),
    CONTACT_INFO_NOT_FOUND("CNT003", HttpStatus.NOT_FOUND);

    private final String code;
    private final HttpStatus httpStatus;
}