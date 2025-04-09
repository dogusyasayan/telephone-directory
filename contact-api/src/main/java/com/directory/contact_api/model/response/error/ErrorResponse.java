package com.directory.contact_api.model.response.error;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private final String exception;
    private final List<String> errors;
    private final long timestamp;
}