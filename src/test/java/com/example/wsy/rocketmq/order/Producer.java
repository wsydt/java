package com.example.wsy.rocketmq.order;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Producer {
    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, RemotingException, InterruptedException, MQBrokerException {
        //设置生产者发送到指定的组中
        DefaultMQProducer producer = new DefaultMQProducer("My_Group_Order_Test");
        //设置rocketmq 的nameserver
        producer.setNamesrvAddr(MyConfig.nameServer);
        producer.start();
        String[] tags = {"TagA", "TagB", "TagC","TagD"};
        List<Map<String, Object>> data = getData();
        for (int i = 0; i < data.size(); i++) {
            Message message = new Message("TopicTest", tags[i % tags.length], data.get(i).toString().getBytes(RemotingHelper.DEFAULT_CHARSET));
            //发送到指定的queue中, 保证mq 的顺序消费, 一个queue对应一个consumer
            SendResult sendResult = producer.send(message, new MessageQueueSelector(){

                /**
                 *
                 * @param list 当前mq 中的队列数量,
                 * @param message
                 * @param o 用于指定指定的queue, 保证需要类型需要顺序的消息 发送到相同的queue中
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    Integer arg = (Integer) o;
                    int index = arg % list.size();
                    System.out.println("选取到的队列索引index : " + index);
                    return list.get(index);
                }
            }, data.get(i).get("key"));

            System.out.println(String.format("SendResult status:%s, queueId:%d, body:%s",
                    sendResult.getSendStatus(),
                    sendResult.getMessageQueue().getQueueId(),
                    data.get(i).toString()));
        }

    }

    private static List<Map<String, Object>> getData (){
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < 10; i ++) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", i + 1);
            map.put("name", "tom" + i);
            map.put("age", 20 + i);
            map.put("key", 789456123);
            data.add(map);
        }
        return data;
    }
}
