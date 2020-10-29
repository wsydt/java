package com.example.wsy.activemq.example.discovery;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class ConsumerAndProducerDiscovery {

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        try {
            connectionFactory = new ActiveMQConnectionFactory("discovery:(multicast://default)");
            connection = connectionFactory.createConnection("admin", "admin");
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue3");
            MessageProducer producer = session.createProducer(destination);
            String msg = "Hello WSYDT LLLLLLLLLLL 一二三";
            TextMessage message = session.createTextMessage(msg);
            producer.send(message);

            MessageConsumer consumer = session.createConsumer(destination);
            Message receive = consumer.receive();
            if (receive instanceof TextMessage) {
                System.out.println(((TextMessage) receive).getText());
            } else {
                System.out.println(receive);
            }
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
