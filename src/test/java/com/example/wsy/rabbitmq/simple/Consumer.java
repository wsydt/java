package com.example.wsy.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private static Runnable runnable = () -> {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接属性
        connectionFactory.setHost("172.18.89.173");
        connectionFactory.setUsername("wsy");
        connectionFactory.setPassword("wsydt");
        Connection connection = null;
        Channel channel = null;
        final String clientName = "consumer-" + Thread.currentThread().getName();
        String queueName = "simple_queue";

        try {
            //获取一个新的连接
            connection = connectionFactory.newConnection(clientName);
            //从连接中获取通道
            channel = connection.createChannel();
            //声明一个队列, 第二个参数 : 是否持久化; 第三个参数 : 是否独占; 第四个参数 : 是否自动删除;
            channel.queueDeclare(queueName, true, false, false, null);

            //定义消息接收回调对象
            DeliverCallback callback = (s, delivery) -> {
                System.out.println(clientName + " accept data : " + new String(delivery.getBody()));
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            //监听队列
            channel.basicConsume(queueName, true, callback, s -> {

            });
            System.out.println(clientName + " start receive message ...");
            new CountDownLatch(1).await();
        } catch (InterruptedException | TimeoutException | IOException e) {
            e.printStackTrace();
        } finally {
            // 8、关闭通道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }

            // 9、关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    public static void main(String[] args) {
        new Thread(runnable, "1").start();
        new Thread(runnable, "2").start();
    }
}
