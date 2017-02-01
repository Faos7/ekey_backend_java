package com.stepping.step5.configs;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * Created by root on 1/31/17.
 */
@Configurable
public class MailConfig {

    @Value("S{mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.username}")
    private String username;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.protocol}")
    private String protocol;

    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    @Value("${mail.smtp.starttls.enable}")
    private String starttsEnable;

    @Value("${mail.debug}")
    private String mailDebug;

    @Value("${mail.from}")
    private String from;

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(username);
        mailSender.setPassword(password);

        Properties prop = mailSender.getJavaMailProperties();
        prop.put("mail.transport.protocol", protocol);
        prop.put("mail.smtp.auth", smtpAuth);
        prop.put("mail.smtp.starttls.enable", starttsEnable);
        prop.put("mail.debug", mailDebug);
        return mailSender;
    }

    @Bean
    public SimpleMailMessage templateMassege(){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setSubject("Your new password");
        return mailMessage;
    }

}
