package com.directory.report_api.service;

import com.directory.report_api.converter.ReportConverter;
import com.directory.report_api.domain.Report;
import com.directory.report_api.exception.ReportNotFoundException;
import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import com.directory.report_api.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class ReportServiceTest {

    @Mock
    private ReportRepository reportRepository;

    @Mock
    private ReportConverter reportConverter;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_return_all_reports() {
        // given
        Report report1 = Report.builder().id(UUID.randomUUID()).build();
        Report report2 = Report.builder().id(UUID.randomUUID()).build();
        List<Report> reports = List.of(report1, report2);

        List<ReportResponse> responses = List.of(
                ReportResponse.builder().id(report1.getId()).build(),
                ReportResponse.builder().id(report2.getId()).build()
        );

        when(reportRepository.findAll()).thenReturn(reports);
        when(reportConverter.convertToResponseList(reports)).thenReturn(responses);

        // when
        List<ReportResponse> result = reportService.getAllReports();

        // then
        assertThat(result).hasSize(2);
        verify(reportRepository).findAll();
        verify(reportConverter).convertToResponseList(reports);
    }

    @Test
    void should_return_report_detail_when_found() {
        // given
        UUID reportId = UUID.randomUUID();
        Report report = Report.builder().id(reportId).build();
        ReportDetailResponse detail = ReportDetailResponse.builder().id(reportId).build();

        when(reportRepository.findById(reportId)).thenReturn(Optional.of(report));
        when(reportConverter.convertToDetail(report)).thenReturn(detail);

        // when
        ReportDetailResponse result = reportService.getReportDetail(reportId);

        // then
        assertThat(result.getId()).isEqualTo(reportId);
        verify(reportRepository).findById(reportId);
        verify(reportConverter).convertToDetail(report);
    }

    @Test
    void should_throw_exception_when_report_not_found() {
        // given
        UUID reportId = UUID.randomUUID();
        when(reportRepository.findById(reportId)).thenReturn(Optional.empty());

        // when / then
        assertThatThrownBy(() -> reportService.getReportDetail(reportId))
                .isInstanceOf(ReportNotFoundException.class);

        verify(reportRepository).findById(reportId);
        verifyNoInteractions(reportConverter);
    }
}
