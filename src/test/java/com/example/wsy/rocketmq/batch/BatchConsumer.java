package com.example.wsy.rocketmq.batch;

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

public class BatchConsumer {

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("Batch_Group");
        consumer.setNamesrvAddr(MyConfig.nameServer);
        consumer.subscribe("TopicTest", "TagY");
        //设置批量处理消息数量, 即每次最多获取多少消息, 默认时一条
        consumer.setConsumeMessageBatchMaxSize(10);
        Random random = new Random();
//        consumer.start();
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            /**
             * 当只有设置了批量处理消息后, list中的message 才会有多条, 否则只有一条数据;
             * @param list
             * @param consumeConcurrentlyContext
             * @return
             */
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt ext : list) {
                    try {
                        System.out.println("consumerThread : " + Thread.currentThread().getName() + " , queueId : " + ext.getMsgId() + " , content : " + new String(ext.getBody(), RemotingHelper.DEFAULT_CHARSET));
//                        Thread.sleep(random.nextInt(100));
                    } catch (UnsupportedEncodingException /*| InterruptedException*/ e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //必须先设置监听 再启动消费者, 不然会报错
        consumer.start();
    }
}
