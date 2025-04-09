package com.directory.report_api.converter;

import com.directory.report_api.domain.Report;
import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportConverter {

    public ReportResponse convertToResponse(Report report) {
        return ReportResponse.builder()
                .id(report.getId())
                .status(report.getStatus())
                .requestedAt(report.getRequestedAt())
                .build();
    }

    public List<ReportResponse> convertToResponseList(List<Report> reports) {
        return reports.stream()
                .map(this::convertToResponse)
                .toList();
    }

    public ReportDetailResponse convertToDetail(Report report) {
        return ReportDetailResponse.builder()
                .id(report.getId())
                .status(report.getStatus())
                .requestedAt(report.getRequestedAt())
                .location(report.getLocation())
                .contactCount(report.getContactCount())
                .phoneNumberCount(report.getPhoneNumberCount())
                .build();
    }
}
