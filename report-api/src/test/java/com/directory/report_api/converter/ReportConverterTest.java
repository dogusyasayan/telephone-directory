package com.directory.report_api.converter;

import com.directory.report_api.domain.Report;
import com.directory.report_api.domain.enums.ReportStatus;
import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class ReportConverterTest {

    private ReportConverter reportConverter;

    @BeforeEach
    void setUp() {
        reportConverter = new ReportConverter();
    }

    @Test
    void should_convert_report_to_reportResponse() {
        // given
        UUID reportId = UUID.randomUUID();
        Report report = Report.builder()
                .id(reportId)
                .status(ReportStatus.COMPLETED)
                .requestedAt(LocalDateTime.now())
                .build();

        // when
        ReportResponse response = reportConverter.convertToResponse(report);

        // then
        assertThat(response.getId()).isEqualTo(reportId);
        assertThat(response.getStatus()).isEqualTo(ReportStatus.COMPLETED);
        assertThat(response.getRequestedAt()).isEqualTo(report.getRequestedAt());
    }

    @Test
    void should_convert_report_list_to_reportResponse_list() {
        // given
        Report report1 = Report.builder().id(UUID.randomUUID()).status(ReportStatus.COMPLETED).requestedAt(LocalDateTime.now()).build();
        Report report2 = Report.builder().id(UUID.randomUUID()).status(ReportStatus.PREPARING).requestedAt(LocalDateTime.now()).build();
        List<Report> reports = List.of(report1, report2);

        // when
        List<ReportResponse> responses = reportConverter.convertToResponseList(reports);

        // then
        assertThat(responses).hasSize(2);
        assertThat(responses.get(0).getId()).isEqualTo(report1.getId());
            assertThat(responses.get(1).getStatus()).isEqualTo(ReportStatus.PREPARING);
    }

    @Test
    void should_convert_report_to_reportDetailResponse() {
        // given
        Report report = Report.builder()
                .id(UUID.randomUUID())
                .status(ReportStatus.COMPLETED)
                .requestedAt(LocalDateTime.now())
                .location("İzmir")
                .contactCount(5)
                .phoneNumberCount(2)
                .build();

        // when
        ReportDetailResponse detail = reportConverter.convertToDetail(report);

        // then
        assertThat(detail.getId()).isEqualTo(report.getId());
        assertThat(detail.getStatus()).isEqualTo(ReportStatus.COMPLETED);
        assertThat(detail.getLocation()).isEqualTo("İzmir");
        assertThat(detail.getContactCount()).isEqualTo(5);
        assertThat(detail.getPhoneNumberCount()).isEqualTo(2);
    }
}
