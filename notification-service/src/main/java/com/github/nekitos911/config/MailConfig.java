package com.github.nekitos911.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@RequiredArgsConstructor
@Configuration
@PropertySource("classpath:mail-props.properties")
public class MailConfig {
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    @Value("${mail.transport.protocol}")
    private String transportProtocol;

    @Value("${mail.smtp.auth}")
    private String smtpAuth;

    @Value("${mail.smtp.strattls.enable}")
    private boolean strattlsEnable;

    @Value("${mail.debug}")
    private boolean isDebug;

    @Bean
    public JavaMailSender javaMailSender() {
        var mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        var props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", transportProtocol);
        props.put("mmail.smtp.auth", smtpAuth);
        props.put("mmail.smtp.strattls.enable", strattlsEnable);
        props.put("mail.debug", isDebug);
        return mailSender;
    }
}
