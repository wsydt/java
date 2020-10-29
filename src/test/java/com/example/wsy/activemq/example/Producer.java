package com.example.wsy.activemq.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class Producer {

    private static String broker = "tcp://172.18.89.173:61616";
    private static String destinationUrl = "queue2";
    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        MessageProducer producer;
        try {
            //创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(broker);
            //创建连接对象
            connection = connectionFactory.createConnection("admin", "admin");
            connection.start(); //连接需要启动
            //创建会话session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //点对点发送目标
            Destination destination = session.createQueue(destinationUrl);
            //创建生产者
            producer = session.createProducer(destination);
            //设置生产者模式(是否持久化)
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建发送对象
            for (int i = 0;i < 10; i++) {
                String msg = "Hello wsydt! " + i ;
                TextMessage message = session.createTextMessage(msg);

                producer.send(message);
            }
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
