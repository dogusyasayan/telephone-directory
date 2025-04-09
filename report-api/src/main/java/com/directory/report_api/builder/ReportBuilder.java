package com.directory.report_api.builder;

import com.directory.report_api.domain.Report;
import com.directory.report_api.domain.enums.ReportStatus;
import com.directory.report_api.model.response.ContactDetailResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component

public class ReportBuilder {

    public Report build(ContactDetailResponse contactDetail) {
        String location = contactDetail.getLocation();
        int phoneCount = contactDetail.getPhone() != null ? 1 : 0;

        return Report.builder()
                .contactId(contactDetail.getId())
                .requestedAt(LocalDateTime.now())
                .location(location)
                .contactCount(1)
                .phoneNumberCount(phoneCount)
                .status(ReportStatus.COMPLETED)
                .build();
    }
}