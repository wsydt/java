package com.example.wsy.rocketmq.order;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

public class Consumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("My_Group_Order_Test");
        consumer.setNamesrvAddr(MyConfig.nameServer);
        consumer.subscribe("TopicTest", "TagA || TagB || TagC || TagD");
        Random random = new Random();

        consumer.registerMessageListener(new MessageListenerOrderly() {

            //消费者注册并发消息消费监听器
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                for (MessageExt msg : list) {
//                    try {
//                        System.out.println("consumerThread : " + Thread.currentThread().getName() + " , queueId : " + msg.getMsgId() + " , content : " + new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
//                        Thread.sleep(random.nextInt(100));
//                    } catch (UnsupportedEncodingException | InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
            //消费者注册顺序消息消费监听器
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    try {
                        System.out.println("consumerThread : " + Thread.currentThread().getName() + " , queueId : " + msg.getMsgId() + " , content : " + new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                        Thread.sleep(random.nextInt(100));
                    } catch (UnsupportedEncodingException | InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        consumer.start();

    }
}
