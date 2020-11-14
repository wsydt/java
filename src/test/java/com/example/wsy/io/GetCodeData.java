package com.example.wsy.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetCodeData {

    public static void main(String[] args) throws IOException {
        String path = "F:\\Code\\";
        File director = new File(path);
        String[] files = director.list();
        if (files != null) {
            List<Object> code = new ArrayList<>(32);
            List<Object> value = new ArrayList<>(32);
            for (String name : files) {
                code.clear();
                value.clear();
                String fileName = path + name;
                BufferedReader in = new BufferedReader(new FileReader(fileName));
                String read;
                while((read = in.readLine()) != null) {
                    String[] data = read.split("=");
                    code.add(data[0].trim());
                    value.add(data[1].trim().replaceAll("\"", ""));
                }
                System.out.println(name + "code:");
                for (Object o : code) {
                    System.out.println(o);
                }
                System.out.println(name + "value:");
                for (Object o : value) {
                    System.out.println(o);
                }
                System.out.println("-----------------------------------------------------next file context --------------------------------------------------------------------");
            }



        }

    }

}
