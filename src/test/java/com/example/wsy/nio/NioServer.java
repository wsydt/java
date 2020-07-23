package com.example.wsy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class NioServer {

    private static LinkedBlockingQueue<SocketChannel> channels = new LinkedBlockingQueue<>();

    public static void main(String [] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(10032));
        while(true) {
            SocketChannel accept = serverSocketChannel.accept();
            if (accept != null) {
                System.out.println("accept request : " + accept.toString());
                accept.configureBlocking(false);
                channels.offer(accept);
            } else {
                Iterator<SocketChannel> iterator = channels.iterator();
                while(iterator.hasNext()){
                    ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                    accept = iterator.next();
                    if (accept.isOpen() && accept.read(requestBuffer) == 0) {
                        continue;
                    }
                    while (accept.isOpen() && accept.read(requestBuffer) != -1) {
                        if (requestBuffer.position() > 0) {
                            break;
                        }
                    }
                    if (requestBuffer.position() == 0) {
                        continue;
                    }
                    requestBuffer.flip();
                    byte[] content = new byte[requestBuffer.limit()];
                    requestBuffer.get(content);
                    System.out.println("accept data : ");
                    System.out.println(new String(content));
                    String responseData = "HTTP/1.1 OK 200\r\n"
                            + "Content-Length : 22\r\n\r\n"
                            + "ni shi yi ge da sha bi";
                    ByteBuffer responsebuffer = ByteBuffer.wrap(responseData.getBytes());
                    accept.write(responsebuffer);
                    System.out.println("response success");
                }
            }
        }
    }
}
