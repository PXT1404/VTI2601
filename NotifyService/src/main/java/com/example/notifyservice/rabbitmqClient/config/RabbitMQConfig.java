package com.example.notifyservice.rabbitmqClient.config;


import com.example.notifyservice.rabbitmqClient.constants.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue accountQueue() {
        return new Queue(Constants.QUEUE_ACCOUNT_CREATED);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(Constants.EXCHANGE);
    }
    
    @Bean
    public Binding bindingAccountQueue() {
        return BindingBuilder.bind(accountQueue())
                .to(exchange())
                .with(Constants.ROUTING_KEY_ACCOUNT);
    }
}
