package com.trainig.spring.main.project.component.schedule;

import com.trainig.spring.main.project.entity.Addressee;
import com.trainig.spring.main.project.service.AddresseeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleEmailMessage {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private AddresseeService addresseeService;

    private static final String MESSAGE = "Hello %s! This is the tea time";

    @Scheduled(cron = "0 0 17 * * MON-FRI", zone = "Asia/Dubai")
    private void tryToSendEmail() {
        List<Addressee> addressees = addresseeService.getAllAddressee();
        for (Addressee a : addressees) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(a.getAddresseeEmail());
            message.setSubject("Attention");
            message.setText(String.format(MESSAGE, a.getAddresseeName()));
            emailSender.send(message);
        }
    }

}
