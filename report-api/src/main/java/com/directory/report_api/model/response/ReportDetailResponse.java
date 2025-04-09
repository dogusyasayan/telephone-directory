package com.directory.report_api.model.response;

import com.directory.report_api.domain.enums.ReportStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDetailResponse {
    private UUID id;
    private String location;
    private int contactCount;
    private int phoneNumberCount;
    private ReportStatus status;
    private LocalDateTime requestedAt;
}
