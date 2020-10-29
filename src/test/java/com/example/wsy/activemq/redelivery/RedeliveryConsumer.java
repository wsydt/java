package com.example.wsy.activemq.redelivery;

import lombok.val;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

public class RedeliveryConsumer {

    private static String brokerUrl = "tcp://172.18.89.173:61616";
    public static void main(String[] args) {
        ActiveMQConnectionFactory connectionFactory;
        Connection connection;
        Session session;
        try {
            //定义消息重发策略
            RedeliveryPolicy redeliveryPolicy = new RedeliveryPolicy();
            redeliveryPolicy.setRedeliveryDelay(5000L); //第一次重发后的延迟时间
            redeliveryPolicy.setInitialRedeliveryDelay(0L); //重发初始化延迟时间
            redeliveryPolicy.setUseExponentialBackOff(false);// 是否以指数递增的方式增加超时时间
            redeliveryPolicy.setMaximumRedeliveries(2); //设置最大重发次数, 为-1 则不使用最大重发次数
            //创建连接工厂
            connectionFactory = new ActiveMQConnectionFactory(brokerUrl);
            //设置消息重发策略
            connectionFactory.setRedeliveryPolicy(redeliveryPolicy);
            connection = connectionFactory.createConnection("admin", "admin");
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue("queue2");
            MessageConsumer consumer = session.createConsumer(destination);

            consumer.setMessageListener((message -> {
                try {
                    if (message instanceof TextMessage) {
                        String content = ((TextMessage) message).getText();
                        System.out.println(content);
                        if (content.endsWith("4")){
                            throw new RuntimeException("redelivery message");
                        }
                    }
                } catch (JMSException e) {
                    e.printStackTrace();
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                    try {
                        // 消息重发
                        session.recover();
                    } catch (JMSException e1) {
                        e1.printStackTrace();
                    }
                }
            }));

        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
