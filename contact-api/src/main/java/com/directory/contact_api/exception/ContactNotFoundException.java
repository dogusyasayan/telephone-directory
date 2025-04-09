package com.directory.contact_api.exception;

import com.directory.contact_api.exception.enums.ErrorStatus;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(ErrorStatus errorStatus) {
        super(String.valueOf(errorStatus));
    }
}