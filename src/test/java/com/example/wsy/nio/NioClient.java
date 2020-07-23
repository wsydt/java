package com.example.wsy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NioClient {
    public static void main (String [] args)  throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 10032));
        while (!socketChannel.finishConnect()) {
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("input data : ");
        String msg = scanner.nextLine();
        ByteBuffer requestBuffer = ByteBuffer.wrap(msg.getBytes());
        while(requestBuffer.hasRemaining()){
            socketChannel.write(requestBuffer);
        }
        System.out.println("data send complete !");
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
        while(socketChannel.isOpen() && socketChannel.read(responseBuffer) != -1) {
            if (responseBuffer.position() > 0) {
                break;
            }
        }
        responseBuffer.flip();
        byte [] responseByte = new byte[responseBuffer.limit()];
        responseBuffer.get(responseByte);
        System.out.println("server respomnse : ");
        System.out.println(new String(responseByte));
        scanner.close();
        socketChannel.close();
    }

}
