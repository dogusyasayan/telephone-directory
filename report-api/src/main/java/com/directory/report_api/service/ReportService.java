package com.directory.report_api.service;

import com.directory.report_api.converter.ReportConverter;
import com.directory.report_api.domain.Report;
import com.directory.report_api.exception.ReportNotFoundException;
import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import com.directory.report_api.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final ReportConverter reportConverter;

    @Transactional(readOnly = true)
    public List<ReportResponse> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reportConverter.convertToResponseList(reports);
    }

    @Transactional(readOnly = true)
    public ReportDetailResponse getReportDetail(UUID reportId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(ReportNotFoundException::new);
        return reportConverter.convertToDetail(report);
    }
}
