package com.example.wsy.rocketmq.transaction;

import com.example.wsy.rocketmq.common.MyConfig;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;

public class TransactionProducer {

    private static TransactionListener transactionListener = new TransactionListener() {

        /**
         * 发送消息成功时执行本地事务
         * @param message
         * @param o  producer.sendMessageInTransaction(message, o) 代表第二个参数
         * @return
         */
        @Override
        public LocalTransactionState executeLocalTransaction(Message message, Object o) {
            // TODO 开启本地事务（实际就是我们的jdbc操作）

            // TODO 执行业务代码（插入订单数据库表）
            // int i = orderDatabaseService.insert(....)
            // TODO 提交或回滚本地事务(如果用spring事务注解，这些都不需要我们手工去操作)
            int index = 2;
            switch(index) {
                case 3 :
                    System.out.printf("事务回滚, 回滚信息 :id:%s %n", message.getKeys());
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                case 5:
                case 8:
                    return LocalTransactionState.UNKNOW;
                default:
                    System.out.println("提交事务, 消息正常处理");
                    return LocalTransactionState.COMMIT_MESSAGE;
            }
        }

        /**
         * broker 端对未确认的消息进行回查, 将消息发送到对应的Producer端(同一个group下的同一个producer)
         * 由 Producer 根据消息来检查本地事务的状态, 进而执行 Commit 或者 RollBack
         * @param messageExt
         * @return
         */
        @Override
        public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
            //根据业务进行正确处理, 只要数据库中存在这条数据, 就应该执行 Commit
            String transactionId = messageExt.getTransactionId();
            String key = messageExt.getKeys();
            System.out.printf("回查事务状态 key:%-5s msgId:%-10s transactionId:%-10s %n", key, messageExt.getMsgId(), transactionId);
            if ("id_5".equals(key)) {
                System.out.printf("回查到本地事务已提交，提交消息，id:%s%n", messageExt.getKeys());
                return LocalTransactionState.COMMIT_MESSAGE;
            } else {
                System.out.printf("未查询到本地事务状态，回滚消息，id:%s%n", messageExt.getKeys());
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        }
    };


    public static void main(String[] args) throws MQClientException, UnsupportedEncodingException, InterruptedException {
        //使用事务生产者对象
        TransactionMQProducer producer = new TransactionMQProducer("GROUP_TEST_MY");
        producer.setNamesrvAddr(MyConfig.nameServer);
        //设置食物监听器
        producer.setTransactionListener(transactionListener);
        producer.start();
        for (int i = 0; i < 10; i ++) {
            Message message = new Message("TopicTest", "TagP","id_"+ i ,("hello transaction wsydt" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            //发送食物消息
            SendResult result = producer.sendMessageInTransaction(message, 3);
            System.out.printf("发送结果：%s%n", result);
        }
//        producer.shutdown();
        new CountDownLatch(1).await();
    }

}
