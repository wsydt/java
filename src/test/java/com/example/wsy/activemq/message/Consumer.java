package com.example.wsy.activemq.message;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Consumer {

    private static String broker = "tcp://172.18.89.173:61616";

    private static String destinationUrl ="queue3";

    public static void main(String[] args) {
        Connection connection;
        ActiveMQConnectionFactory connectionFactory;
        Session session;
        MessageConsumer consumer;
        try {
            //创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(broker);
            //创建连接对象
            connection = connectionFactory.createConnection("admin", "admin");
            //连接一定要启动
            connection.start();
            //创建会话,(一个或者多个)
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建目标 queue
            Destination destination = session.createQueue(destinationUrl);
            //创建消费者信息
            consumer = session.createConsumer(destination);
            //接收消息对象 (如果当前队列 无消息一直等待)
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    try {
                        System.out.println("收到文本信息 : " + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(message);
                }
            });

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
