package com.directory.report_api.domain;

import com.directory.report_api.domain.enums.ReportStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {

    @Id
    @GeneratedValue
    private UUID id;
    private UUID contactId;
    private String location;
    private Integer contactCount;
    private Integer phoneNumberCount;
    private LocalDateTime requestedAt;
    @Enumerated(EnumType.STRING)
    private ReportStatus status;
}