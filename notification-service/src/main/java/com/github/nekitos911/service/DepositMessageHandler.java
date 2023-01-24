package com.github.nekitos911.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import static com.github.nekitos911.config.RabbitMqConfig.DEPOSIT_QUEUE;

public interface DepositMessageHandler {
    @RabbitListener(queues = DEPOSIT_QUEUE)
    void receive(Message message) throws JsonProcessingException;
}
