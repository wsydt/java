package com.example.wsy.activemq.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttConsumer {
    private static int qos = 2;
    private static String broker = "tcp://172.18.89.173:1883";
    private static String userName = "admin";
    private static String password = "admin";

    private static MqttClient connect(String clientId) throws MqttException {
        MemoryPersistence persistence = new MemoryPersistence();
        MqttConnectOptions connectOptions = new MqttConnectOptions();
        connectOptions.setCleanSession(false);
        connectOptions.setUserName(userName);
        connectOptions.setPassword(password.toCharArray());
        connectOptions.setConnectionTimeout(10);
        connectOptions.setKeepAliveInterval(20);
        MqttClient mqttClient = new MqttClient(broker, clientId, persistence);
        mqttClient.connect(connectOptions);
        return mqttClient;
    }

    public static void main(String[] args) throws MqttException {
        MqttClient mqttClient = connect("consumer-client-id-1");
        if (mqttClient != null) {
            int[] Qos = {qos};
            String[] topics = {"x/y/z"};
            mqttClient.subscribe(topics, Qos, new IMqttMessageListener[]{(s, mqttMessage)->{
                System.out.println("accept data : " + s + " > " + mqttMessage.toString());
            }});
        }
    }

}
