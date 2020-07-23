package com.example.wsy.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class SocketClient {

    public static void main(String [] args) throws Exception  {
        Socket socket = new Socket("localhost", 10032);
        Scanner scanner = new Scanner(System.in);
        OutputStream outputStream = socket.getOutputStream();
        String msg = null;
        int num = 10;
        System.out.println("input data : ");
        msg = scanner.nextLine();
        byte[] request = new byte[220];
        String finalMsg = msg + "  门前大桥下 游过一群鸭";
        System.arraycopy(msg.getBytes(),0,  request, 0, msg.getBytes().length );
        System.arraycopy(finalMsg.getBytes(),0,  request, msg.getBytes().length, finalMsg.getBytes().length );

        CountDownLatch latch = new CountDownLatch(num);
        for(int i = 0; i< num;i++) {
//            String finalMsg = msg;

            new Thread(()->{
                try {
                    outputStream.write(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println("send complete ...");
//       InputStream inputStream = socket.getInputStream();
//       byte[] response = new byte[1024];
//       inputStream.read(response);
//       System.out.println(new String (response));

    }
}
