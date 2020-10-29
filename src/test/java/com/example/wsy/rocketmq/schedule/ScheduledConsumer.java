package com.example.wsy.rocketmq.schedule;

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
import java.util.Random;

public class ScheduledConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Schedule_Group");
        consumer.setNamesrvAddr(MyConfig.nameServer);
        consumer.subscribe("TopicTest","TagQ");
        Random random = new Random();
        consumer.registerMessageListener(new MessageListenerConcurrently(){

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : list) {
                    try {
                        System.out.println("consumerThread : " + Thread.currentThread().getName() + " , queueId : " + msg.getMsgId() + " , content : " + new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET));
                        Thread.sleep(random.nextInt(100));
                    } catch (UnsupportedEncodingException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
