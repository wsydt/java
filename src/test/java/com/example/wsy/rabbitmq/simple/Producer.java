package com.example.wsy.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static Runnable runnable = ()->{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.18.89.173");
        connectionFactory.setUsername("wsy");
        connectionFactory.setPassword("wsydt");
        Connection connection;
        Channel channel;
        String queueName = "simple_queue";
        try {
            connection = connectionFactory.newConnection("producer - " + Thread.currentThread().getName());
            channel = connection.createChannel();
            String message1 = "Hello wsydt 1!!!";
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message1.getBytes());
            System.out.println("message : " + message1 + " , already send ! ");

            String message2 = "Hello wsydt 222!!!!!!!!!";
            channel.basicPublish("", queueName, MessageProperties.PERSISTENT_TEXT_PLAIN, message2.getBytes());
            System.out.println("message : " + message2 + " , already send ! ");
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    };

    public static void main(String[] args) {
        new Thread(runnable, "1").start();
    }
}
