package com.example.authservice.rabbitmqClient.config;

import com.example.authservice.rabbitmqClient.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    @Bean
    public TopicExchange exchang(){
        return new TopicExchange(Constants.EXCHANGE);
    }

    @Bean
    public Queue accountCreateQueue(){
        return new Queue(Constants.QUEUE_ACCOUNT_CREATED);
    }

    @Bean
    public Binding bindingAccountCreated(Queue accountCreateQueue, TopicExchange exchange) {
        return BindingBuilder.bind(accountCreateQueue).to(exchange).with(Constants.ROUTING_KEY_ACCOUNT);
    }
}
