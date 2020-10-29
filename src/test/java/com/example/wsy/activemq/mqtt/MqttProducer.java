package com.example.wsy.activemq.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttProducer {
    private static int qos = 1;
    private static String broker = "tcp://172.18.89.173:1883";
    private static String userName = "admin";
    private static String password = "admin";

    private static MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        connOpts.setUserName(userName);
        connOpts.setPassword(password.toCharArray());
        connOpts.setConnectionTimeout(10);
        connOpts.setKeepAliveInterval(20);

        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.setCallback(new PushCallback("test"));
        mqttClient.connect(connOpts);
        return mqttClient;
    }

    private static void pub(MqttClient mqttClient, String message, String topic) throws MqttException {
        MqttMessage mqttMessage = new MqttMessage(message.getBytes());
        mqttMessage.setQos(qos);
        mqttMessage.setRetained(false);
        mqttClient.publish(topic, mqttMessage);
    }

    public static void main(String[] args) throws MqttException {
        String message = "Hello wsydt mqtt!!!!!!";
        MqttClient mqttClient = connect("producer-client-id-1");
        if (mqttClient != null) {
            pub(mqttClient, message, "x/y/z");
            System.out.println("pub --> " + message);
        }
        if (mqttClient != null) {
            mqttClient.disconnect();
        }
    }
}
class PushCallback implements MqttCallback{

    private String threadId;

    public PushCallback(String threadId) {
        this.threadId = threadId;
    }

    @Override
    public void connectionLost(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        String msg = new String(mqttMessage.getPayload());
        System.out.println(threadId + " : " + msg);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        System.out.println("server is correct accept : ----" + iMqttDeliveryToken.isComplete());
    }
}

