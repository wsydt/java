package com.example.wsy.rocketmq.transaction;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class TransactionConsumer {

    public static void main(String[] args) throws MQClientException {
//        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("GROUP_TEST_MY");
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Batch_Group");
        consumer.setNamesrvAddr(MyConfig.nameServer);
        consumer.subscribe("TopicTest","");
        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageExt message = list.get(0);
                try {
                    System.out.printf("线程：%-25s 接收到新消息 %s --- %s %n", Thread.currentThread().getName(), message.getTags(), new String(message.getBody(), RemotingHelper.DEFAULT_CHARSET));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("consumer start ...");
    }

}
