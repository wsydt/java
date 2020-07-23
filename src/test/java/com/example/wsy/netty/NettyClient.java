package com.example.wsy.netty;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class NettyClient {

    public static void main(String [] args) throws Exception  {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("localhost", 10032));
        while (!socketChannel.finishConnect()){
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        String msg = null;
        int num = 1;
        System.out.println("input data : ");
        msg = scanner.nextLine();
        CountDownLatch latch = new CountDownLatch(num);
        for(int i = 0; i< num;i++) {
//            String finalMsg = msg;
            String finalMsg = msg + "  门前大桥下 游过一群鸭";
            new Thread(()->{
                ByteBuffer requestBuffer = ByteBuffer.allocate(220);
//                ByteBuffer requestBuffer = ByteBuffer.wrap(finalMsg.getBytes());
                requestBuffer.put(finalMsg.getBytes());
                System.out.println(new String(requestBuffer.array()));

                while(requestBuffer.hasRemaining()) {
                    try {
                        socketChannel.write(requestBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("send complete ...");
        //读取响应数据
        ByteBuffer responseBuffer = ByteBuffer.allocate(1024);
        while(socketChannel.isOpen() && socketChannel.read(responseBuffer) != -1) {
            if (responseBuffer.position() > 0) {
                break;
            }
        }
        responseBuffer.flip();
        byte[] connect = new byte[responseBuffer.limit()];
        responseBuffer.get(connect);
        System.out.println("server response : ");
        System.out.println(new String(connect));
        scanner.close();
        socketChannel.close();
    }
}
