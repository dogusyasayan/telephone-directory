package com.directory.report_api.controller;

import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import com.directory.report_api.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping
    public List<ReportResponse> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{reportId}")
    public ReportDetailResponse getReportDetail(@PathVariable UUID reportId) {
        return reportService.getReportDetail(reportId);
    }
}