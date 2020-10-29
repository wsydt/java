package com.example.wsy.rabbitmq.durable;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeoutException;

/**
 * rabbitmq持久化示例
 */
public class Consumer {

    private static Runnable runnable = new Runnable(){
        @Override
        public void run() {
            //创建连接工厂
            ConnectionFactory connectionFactory = new ConnectionFactory();
            //设置连接属性
            connectionFactory.setHost("172.18.89.173");
            connectionFactory.setUsername("wsy");
            connectionFactory.setPassword("wsydt");
            Connection connection = null;
            Channel channel = null;
            final String clientName = "consumer-" + Thread.currentThread().getName();
            String queueName = "routing_test_queue2";

            try {
                //获取一个新的连接
                connection = connectionFactory.newConnection(clientName);
                //从连接中获取通道
                channel = connection.createChannel();
                //声明一个exchange , 第二个参数: exchange类型: fanout, direct, topic, header 四种类型, 第三个参数 : 是否持久化;
                channel.exchangeDeclare("routing_test2", "direct", true);
                //声明一个队列, 第二个参数 : 是否持久化; 第三个参数 : 是否独占; 第四个参数 : 是否自动删除;
                channel.queueDeclare(queueName, true, false, false, null);
                //将队列绑定到对应的exchange上, 第二个参数 : exchange 名称; 第三个参数 : route_key, 对应的路由键, 通过此路由键决定接收谁的消息;
                channel.queueBind(queueName, "routing_test2", clientName);

                //定义消息接收回调对象
                DeliverCallback callback = (s, delivery) -> {
                        System.out.println(clientName + "accept data : " + new String(delivery.getBody()));
                };
                //监听队列
                channel.basicConsume(queueName, true, callback, new CancelCallback(){

                    @Override
                    public void handle(String s) throws IOException {

                    }
                });
                System.out.println(clientName + " start recive message ...");
                new CountDownLatch(1).await();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if(channel != null && channel.isOpen()) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (TimeoutException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null && connection.isOpen()) {
                    try {
                        connection.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    };

    public static void main(String[] args){
        new Thread(runnable, "1").start();
        new Thread(runnable, "2").start();
    }
}
