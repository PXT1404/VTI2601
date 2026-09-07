package com.example.notifyservice.rabbitmqClient.consumer;


import com.example.notifyservice.rabbitmqClient.constants.Constants;
import com.example.notifyservice.rabbitmqClient.service.NotifyHandler;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountEventConsumer {

    @Autowired
    private NotifyHandler notifyHandler;

    @RabbitHandler
    @RabbitListener(queues = Constants.QUEUE_ACCOUNT_CREATED)
    public void receiveMessage(String message) {
        System.out.println(" Received message From AccountService: " + message);
        notifyHandler.handleAccountNotification(message);
    }
}
