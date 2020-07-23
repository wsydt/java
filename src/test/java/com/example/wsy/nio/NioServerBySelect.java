package com.example.wsy.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerBySelect {
    public static void main(String [] args)  throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        SelectionKey selectionKey = serverSocketChannel.register(selector, 0, serverSocketChannel);
        selectionKey.interestOps(SelectionKey.OP_ACCEPT);

        serverSocketChannel.bind(new InetSocketAddress(10032));

        System.out.println("server start success ...");

        while (true) {

            selector.select();

            Set<SelectionKey> selectionkeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionkeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()){
                    ServerSocketChannel server = (ServerSocketChannel) key.attachment();
                    SocketChannel  client = server.accept();
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ, client);
                    System.out.println("accept new request : " + client.toString());
                }  else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.attachment();
                    try {
                        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
                        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
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
                        System.out.println(new String(content));
                        System.out.println("accept data from : " + socketChannel.toString());
                        String responseData = "HTTP/1.1 OK 200\r\n"
                                + "Content-Length : 22\r\n\r\n"
                                + "ni shi yi ge da sha bi";
                        ByteBuffer responsebuffer = ByteBuffer.wrap(responseData.getBytes());
                        socketChannel.write(responsebuffer);
                        System.out.println("response success");
                    } catch (IOException e) {
                        e.printStackTrace();
                        key.cancel();
                    }
                }
            }

        }
    }
}
