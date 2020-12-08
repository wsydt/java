package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CodeSynchronize {

    private static List<String> notExist = new ArrayList<>();

    private static List<String> alreadyRemoveKeys = new ArrayList<>();

    private static final String filePath = "F:\\workspace-n\\station-web-exp\\src\\main\\resources\\messages";

    private static final String standardFileName = "message_zh_CN.properties";

    private static List<String> keys = new ArrayList<>();

    private static Set<String> keysSet = new HashSet<>();

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        getStandardFileData();
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "\\message.properties"));
        String text;
        int i = 0;
        while ((text = reader.readLine()) != null) {
            i ++;
            text = text.trim();
            if (text.startsWith("#") || !text.contains("=")) {
                continue;
            }
            if (!StringUtils.isEmpty(text)){
                String[] data = text.split("=");
                boolean flag = keys.remove(data[0]);
                keysSet.remove(data[0]);
                if (!flag) {
                    if (!alreadyRemoveKeys.contains(data[0])) {
                        notExist.add(data[0]);
                    }
                } else {
                    alreadyRemoveKeys.add(data[0]);
                }
            }
        }
        keys.removeAll(alreadyRemoveKeys);
        System.out.println("need add keys : ");
        System.out.println(keys);
        System.out.println(keys.size());
        System.out.println(" no repeat keys : ");
        System.out.println(keysSet);
        System.out.println(keysSet.size());
        System.out.println("not exist keys : ");
        System.out.println(notExist);
        System.out.println(notExist.size());


        BufferedReader reader1 = new BufferedReader(new FileReader("E:\\国际化文件\\message.txt"));
        while((text = reader1.readLine()) != null) {
            text = text.trim();
            if (text.startsWith("#") || !text.contains("=")) {
                continue;
            }
            String[] data = text.split("=");
            if (keys.contains(data[0])) {
//                sb.append(text).append("\n");
                sb.append(data[0]).append("=").append(data[0]).append("\n");
            }
        }
        if (sb.length() > 0) {
            System.out.println(sb);
        }



    }

    private static void getStandardFileData() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath + "\\" + standardFileName));
        String text;
        while ((text = reader.readLine()) != null) {
            text = text.trim();
            if (text.startsWith("#") || !text.contains("=")) {
                continue;
            }
            if (!StringUtils.isEmpty(text)){
                String[] data = text.split("=");
                keys.add(data[0]);
                keysSet.add(data[0]);
            }
        }
    }

}
