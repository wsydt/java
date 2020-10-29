package com.example.wsy.rocketmq.schedule;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class ScheduleProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException, UnsupportedEncodingException {
        DefaultMQProducer producer = new DefaultMQProducer("Schedule_Group");
        producer.setNamesrvAddr(MyConfig.nameServer);
        producer.start();
        for (int i = 0; i < 10; i ++) {
            Message message = new Message("TopicTest", "TagQ", ("Hello wsydt !!!" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //设置消息的延迟时间
            message.setDelayTimeLevel(3);
            producer.send(message);
            Thread.sleep(new Random().nextInt(500));
        }
        producer.shutdown();
    }
}
