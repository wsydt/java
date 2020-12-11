package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFileChineseLine {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("F:\\国际化资源文件\\英语(替换后)"));
        Set<String> set = new HashSet<>();
        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]+");
        String read;
        while((read = reader.readLine()) != null) {
            read = read.trim();
            if (StringUtils.isEmpty(read)) {
                continue;
            }
            if (read.startsWith("#") || !read.contains("=")) {
                continue;
            }
            String[] value = read.split("=");
            if (value.length >= 2) {
                Matcher matcher = pattern.matcher(read);
                while (matcher.find()) {
                    if (set.contains(value[0])) {
                        continue;
                    }
                    set.add(value[0]);
                    System.out.println(read);
                }

            }

        }
    }

}
