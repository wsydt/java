package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckUnTransform {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(ReplacePropertiesEntity.outFilePath));
        StringBuilder sb = new StringBuilder();
        List<String> untransForm = new ArrayList<>();
        String read;
        int line = 0;
        while((read = reader.readLine()) != null) {
            line ++;
            read = read.trim();
            if (StringUtils.isEmpty(read)) {
//                sb.append(read).append("\n");
                continue;
            }
            if (read.startsWith("#") || !read.contains("=")) {
//                sb.append(read).append("\n");
                continue;
            }
            String[] value = read.split("=");
            if (value.length >= 2 && value[0].trim().equals(value[1].trim()))  {
//                System.out.println(value[0].trim() + "=" + ReplacePropertiesEntity.cnProperties.get(value[0].trim()).trim());
                sb.append("line : ").append(line).append(", ").append(value[0].trim()).append("=").append(ReplacePropertiesEntity.cnProperties.get(value[0].trim()).trim()).append("\n");
            } else {
//                sb.append(read).append("\n");
            }
        }
        System.out.println(line);
        System.out.println(sb);

//        BufferedWriter writer = new BufferedWriter(new FileWriter("F:\\国际化资源文件\\new 越南语.txt"));
//        writer.write(sb.toString(), 0, sb.length());
//        writer.flush();
//        writer.close();
    }
}
