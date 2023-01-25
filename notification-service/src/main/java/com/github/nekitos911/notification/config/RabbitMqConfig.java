package com.github.nekitos911.notification.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@EnableRabbit
public class RabbitMqConfig {
    public static final String DEPOSIT_QUEUE = "js.deposit.notify";
    private static final String TOPIC_EXCHANGE_DEPOSIT = "js.deposit.notify.exchange";
    private static final String ROUTING_KEY_DEPOSIT = "js.key.deposit";

    private final AmqpAdmin amqpAdmin;

    @Bean
    public TopicExchange depositExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_DEPOSIT);
    }

    @Bean
    public Queue queueDeposit() {
        return new Queue(DEPOSIT_QUEUE);
    }

    @Bean
    public Binding depositBinding() {
        return BindingBuilder.bind(queueDeposit())
                .to(depositExchange())
                .with(ROUTING_KEY_DEPOSIT);
    }
}
