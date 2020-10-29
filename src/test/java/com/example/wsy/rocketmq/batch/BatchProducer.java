package com.example.wsy.rocketmq.batch;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class BatchProducer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        DefaultMQProducer producer = new DefaultMQProducer("Batch_Group");
        producer.setNamesrvAddr(MyConfig.nameServer);
        producer.start();
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 105; i ++) {
            Message message = new Message("TopicTest", "TagY", ("Hello Batch wsydt !!!" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            messages.add(message);
        }
        //消息批量发送
        producer.send(messages);
        producer.shutdown();
    }
}
