package com.example.wsy.activemq.message;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ScheduledMessage;

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
    private static String destinationUrl = "queue3";
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
            String msg1 = "Hello wsydt --- -1 !";
            String msg2 = "Hello wsydt --- -2 !";
            String msg3 = "Hello wsydt --- -3 !";
            TextMessage message1 = session.createTextMessage(msg1);
            message1.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 5000L);

            TextMessage message2 = session.createTextMessage(msg2);
            message2.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, 3000L);
            message2.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_PERIOD, 4000L);
            message2.setIntProperty(ScheduledMessage.AMQ_SCHEDULED_REPEAT, 2);

            TextMessage message3 = session.createTextMessage(msg3);
            message3.setStringProperty(ScheduledMessage.AMQ_SCHEDULED_CRON,"*/1 * * * *");
            producer.send(message1);
            producer.send(message2);
            producer.send(message3);

            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }

}
