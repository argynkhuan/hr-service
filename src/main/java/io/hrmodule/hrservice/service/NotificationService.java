package io.hrmodule.hrservice.service;

import io.hrmodule.hrservice.document.Employee;
import io.hrmodule.hrservice.service.email.EmailConfig;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class NotificationService {

    private EmailConfig emailConfig;

    public void sendMessage(Employee employee) {
        // Create a mail sender
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(this.emailConfig.getHost());
        mailSender.setPort(this.emailConfig.getPort());
        mailSender.setUsername(this.emailConfig.getUsername());
        mailSender.setPassword(this.emailConfig.getPassword());

        // Create an email instance
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(employee.getEmail());
        mailMessage.setTo("argynkhuan@yandex.kz");
        mailMessage.setSubject("New feedback from " + employee.getFullName());
        mailMessage.setText("plain text of email");

        // Send mail
        mailSender.send(mailMessage);
    }
}
