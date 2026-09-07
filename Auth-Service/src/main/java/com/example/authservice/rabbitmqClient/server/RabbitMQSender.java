package com.example.authservice.rabbitmqClient.server;

import com.example.authservice.entity.User;
import com.example.authservice.rabbitmqClient.constants.Constants;
import com.example.authservice.request.UserRegistrationDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendAccountCreatedEvent(UserRegistrationDTO user) {
        String message = user.getFirstName() + user.getLastName()+ ", " +user.getUsername() + ", " + user.getEmail() ;
        rabbitTemplate.convertAndSend(
                Constants.EXCHANGE,
                Constants.ROUTING_KEY_ACCOUNT,
                message);
        System.out.println(" Sent message successfully to RabbitMQ Server: " + message);
    }
}
