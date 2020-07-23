package com.example.wsy.bio;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10032);
        System.out.println("server start ...");
        while (!server.isClosed()) {
            Socket accept = server.accept();
            System.out.println("收到一个连接 : " + accept.toString());
            try {
                InputStream inputStream = accept.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String msg = null;
                while ((msg = reader.readLine()) != null) {
                    if (msg.length() == 0) {
                        break;
                    }
                    System.out.println(msg);
                }
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                outputStream.write("Content-Length: 22\r\n\r\n".getBytes());
                outputStream.write("ni shi yi ge da sha bi".getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                accept.close();
            }
        }
    }
}
