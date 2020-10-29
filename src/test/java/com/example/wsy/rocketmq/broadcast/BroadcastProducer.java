package com.example.wsy.rocketmq.broadcast;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class BroadcastProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("GROUP_TEST");
        producer.setNamesrvAddr(MyConfig.nameServer);
        producer.start();

        int num = 10;
        for (int i = 0; i < num; i ++) {
            Message message = new Message("TopicTest", "TagA", ("Hello wsydt:" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult result = producer.send(message);
            System.out.println(result.toString());
        }
//        try {
//            new CountDownLatch(1).await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }
}
