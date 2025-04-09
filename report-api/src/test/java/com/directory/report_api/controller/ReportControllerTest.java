package com.directory.report_api.controller;

import com.directory.report_api.model.response.ReportDetailResponse;
import com.directory.report_api.model.response.ReportResponse;
import com.directory.report_api.service.ReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void it_should_get_all_reports() throws Exception {
        // when
        ResultActions result = mockMvc.perform(get("/reports"));

        // then
        result.andExpect(status().isOk());
        verify(reportService).getAllReports();
    }

    @Test
    void it_should_get_report_detail() throws Exception {
        // given
        UUID reportId = UUID.randomUUID();

        // when
        ResultActions result = mockMvc.perform(get("/reports/" + reportId)
                .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpect(status().isOk());
        ArgumentCaptor<UUID> captor = ArgumentCaptor.forClass(UUID.class);
        verify(reportService).getReportDetail(captor.capture());
        assertThat(captor.getValue()).isEqualTo(reportId);
    }
}
