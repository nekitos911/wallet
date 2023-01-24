package com.github.nekitos911.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.nekitos911.service.DepositMessageHandler;
import com.github.nekitos911.service.dto.DepositResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepositMessageHandlerImpl implements DepositMessageHandler {
    private final JavaMailSender javaMailSender;
    private final ObjectReader reader = new ObjectMapper().readerFor(DepositResponseDto.class);

    @Override
    public void receive(Message message) throws JsonProcessingException {
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        DepositResponseDto depositResponseDto = reader.readValue(jsonBody);

        var mailMessage = new SimpleMailMessage();
        mailMessage.setTo(depositResponseDto.getEmail());
        mailMessage.setFrom("test@cat.xyz");

        mailMessage.setSubject("Deposit");
        mailMessage.setTo("Make deposit, sum: " + depositResponseDto.getAmount());

        try {
            javaMailSender.send(mailMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
