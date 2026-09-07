package com.example.notifyservice.rabbitmqClient.service;


import org.springframework.stereotype.Service;


@Service
public class NotifyHandler {
    public void handleAccountNotification(String message) {
        System.out.println("You Create New Account Success, Thank you: " + message);
    }
}
