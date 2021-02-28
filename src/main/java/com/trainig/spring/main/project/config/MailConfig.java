package com.trainig.spring.main.project.config;

import com.trainig.spring.main.project.entity.EmailKeeper;
import com.trainig.spring.main.project.service.keeper.KeeperService;
import com.trainig.spring.main.project.utils.EmailConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailConfig {

    private static final Logger log = LoggerFactory.getLogger(MailConfig.class);

    @Autowired
    private KeeperService keeperService;

    @Bean
    public JavaMailSender getJavaMailSender() {
        EmailKeeper emailKeeper = keeperService.getMailKeeper();
        log.info("email keeper mail is {}", emailKeeper.getKeeperEmail());

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(emailKeeper.getKeeperEmail());
        mailSender.setPassword(emailKeeper.getKeeperPassword());
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        return mailSender;
    }

}
