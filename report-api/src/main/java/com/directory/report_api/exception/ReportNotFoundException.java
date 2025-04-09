package com.directory.report_api.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException() {
        super("report not found");
    }
}
