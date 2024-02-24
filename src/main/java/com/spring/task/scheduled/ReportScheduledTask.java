package com.spring.task.scheduled;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ReportScheduledTask {

    private final ReportService reportService;
    private final JavaMailSender mailSender;

    @Value("${spring.report.email.to}")
    private String emailTo;
//    @Scheduled(cron = "0 * * * * ?") // Runs every minute
    @Scheduled(cron = "0 0 1 * * ?") // Runs every day at 01:00 AM
    public void generateAndSendReport() throws MessagingException {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            String reportHtml = reportService.generateReportForDate(yesterday);
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(emailTo);
            helper.setSubject("Daily Report for " + yesterday);
            helper.setText(reportHtml, true);

            mailSender.send(message);
        }
}

