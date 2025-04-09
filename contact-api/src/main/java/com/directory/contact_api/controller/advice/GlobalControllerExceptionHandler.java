package com.directory.contact_api.controller.advice;

import com.directory.contact_api.exception.ContactInfoNotFoundException;
import com.directory.contact_api.exception.ContactNotFoundException;
import com.directory.contact_api.exception.enums.ErrorStatus;
import com.directory.contact_api.model.response.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ContactNotFoundException exception) {
        return instanceError(exception, ErrorStatus.CONTACT_NOT_FOUND);
    }

    @ExceptionHandler(ContactInfoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(ContactInfoNotFoundException exception) {
        return instanceError(exception, ErrorStatus.CONTACT_INFO_NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> instanceError(RuntimeException ex, ErrorStatus errorStatus) {
        log.error("{} exception occurred.", errorStatus.getCode(), ex);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exception(ex.getClass().getSimpleName())
                .errors(Collections.singletonList(errorStatus.getCode()))
                .timestamp(System.currentTimeMillis())
                .build();

        return new ResponseEntity<>(errorResponse, errorStatus.getHttpStatus());
    }
}
