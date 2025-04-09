package com.directory.report_api.rabbitmq.consumer;

import com.directory.report_api.builder.ReportBuilder;
import com.directory.report_api.client.ContactClient;
import com.directory.report_api.domain.Report;
import com.directory.report_api.model.response.ContactDetailResponse;
import com.directory.report_api.rabbitmq.event.ContactCreatedEvent;
import com.directory.report_api.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.directory.report_api.rabbitmq.config.RabbitMQConfig.CONTACT_CREATED_QUEUE;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactCreatedListener {

    private final ReportBuilder reportBuilder;
    private final ReportRepository reportRepository;
    private final ContactClient contactClient;

    @RabbitListener(queues = CONTACT_CREATED_QUEUE)
    public void consume(ContactCreatedEvent event) {
        ContactDetailResponse detail = contactClient.getContactDetail(event.getContactId());
        Report report = reportBuilder.build(detail);
        reportRepository.save(report);
    }
}