package com.example.wsy.activemq.stomp;

import org.apache.activemq.transport.stomp.Stomp;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;

public class ConsumerStomp {

    private static String destionation = "/topic/test-stomp";

    public static void main(String[] args) throws Exception {
        StompConnection connection = new StompConnection();
        connection.open("172.18.89.173", 61613);
        connection.connect("system", "manager");
        connection.subscribe(destionation, Stomp.Headers.Subscribe.AckModeValues.CLIENT);
        while (true) {
            connection.begin("tx2");

            StompFrame message = connection.receive();
            System.out.println(message.getBody());
            connection.ack(message, "tx2");

            message = connection.receive();
            System.out.println(message.getBody());
            connection.ack(message, "tx2");

            connection.commit("tx2");
        }
    }
}
