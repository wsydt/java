package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReadProperties {

    public static void main(String[] args) throws IOException {
        String fileName = "E:\\国际化文件\\message.txt";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
        Map<String, String> map = new HashMap<>(2048);
        while ((line = in.readLine()) != null) {
            if (StringUtils.isEmpty(line = line.trim())) {
                continue;
            }
            if (line.startsWith("#")) {
                continue;
            }
            String[] data = line.split("=");
            if (data.length < 2) {
                continue;
            }
            map.put(data[0], data[1]);
        }

        fileName = ReadFile.outFileName;
        List<String> value = new ArrayList<>();
        in = new BufferedReader(new FileReader(fileName));
        while ((line = in.readLine()) != null) {
            if (StringUtils.isEmpty(line = line.trim())) {
                continue;
            }
            value.add(map.get(line));
        }
        for (String va : value) {
            System.out.println(va);
        }
        System.out.println(value.size());
    }

}
