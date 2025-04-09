package com.directory.report_api.builder;

import com.directory.report_api.domain.Report;
import com.directory.report_api.domain.enums.ReportStatus;
import com.directory.report_api.model.response.ContactDetailResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ReportBuilderTest {

    @InjectMocks
    private ReportBuilder reportBuilder;

    @Test
    void should_build_report_with_valid_contact_detail() {
        // given
        UUID contactId = UUID.randomUUID();
        ContactDetailResponse contactDetail = ContactDetailResponse.builder()
                .id(contactId)
                .name("Ahmet")
                .surname("Yılmaz")
                .company("Yılmazlar A.Ş.")
                .location("İstanbul")
                .email("ahmet@example.com")
                .phone("+905551234567")
                .build();

        // when
        Report result = reportBuilder.build(contactDetail);

        // then
        assertThat(result.getContactId()).isEqualTo(contactId);
        assertThat(result.getLocation()).isEqualTo("İstanbul");
        assertThat(result.getPhoneNumberCount()).isEqualTo(1);
        assertThat(result.getContactCount()).isEqualTo(1);
        assertThat(result.getStatus()).isEqualTo(ReportStatus.COMPLETED);
        assertThat(result.getRequestedAt()).isNotNull();
    }

    @Test
    void should_return_zero_phone_count_when_phone_is_null() {
        // given
        ContactDetailResponse contactDetail = ContactDetailResponse.builder()
                .id(UUID.randomUUID())
                .location("Ankara")
                .phone(null)
                .build();

        // when
        Report result = reportBuilder.build(contactDetail);

        // then
        assertThat(result.getPhoneNumberCount()).isZero();
    }
}
