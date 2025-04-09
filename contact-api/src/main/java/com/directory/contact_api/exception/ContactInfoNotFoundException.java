package com.directory.contact_api.exception;

import com.directory.contact_api.exception.enums.ErrorStatus;

public class ContactInfoNotFoundException extends RuntimeException {
    public ContactInfoNotFoundException(ErrorStatus errorStatus) {
        super(String.valueOf(errorStatus));
    }
}

