package com.example.wsy.activemq.stomp;

import org.apache.activemq.transport.stomp.Stomp;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;

public class ConsumerAndProducerStomp {

    private static String destionation = "/topic/test-stomp";

    public static void main(String[] args) throws Exception {
        StompConnection connection = new StompConnection();

        connection.setVersion("1.2");
        connection.open("172.18.89.173", 61613);
        connection.connect("system", "manager");

        connection.begin("tx1");
        connection.send(destionation, "message1");
        connection.send(destionation, "message2");
        connection.commit("tx1");

        connection.subscribe(destionation, Stomp.Headers.Subscribe.AckModeValues.CLIENT);

        connection.begin("tx2");

        StompFrame message = connection.receive();
        System.out.println(message.getBody());
        connection.ack(message, "tx2");

        message = connection.receive();
        System.out.println(message.getBody());
        connection.ack(message, "tx2");

        connection.commit("tx2");
        connection.disconnect();

    }
}
