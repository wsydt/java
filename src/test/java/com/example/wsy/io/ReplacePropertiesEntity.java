package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReplacePropertiesEntity {

    private static final String filePathVI = "F:\\国际化资源文件\\英语";

    public static Map<String, String> cnProperties = new HashMap<>();
    private static Excel<PropertiesEntity> excel = new Excel<>();
    private static final String filePathCN = "F:\\国际化资源文件\\中文";
    static final String outFilePath = "F:\\国际化资源文件\\替换后的英语.txt";

    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePathCN));
            String read;
            int line = 1;
            while ((read = reader.readLine()) != null) {
//            System.out.println(line ++);
                if (StringUtils.isEmpty(read)) {
                    continue;
                }
                read = read.trim();
                if (read.startsWith("#") || !read.contains("=")) {
                    continue;
                }
                String[] value = read.split("=");
                if (value.length >= 2) {
                    cnProperties.put(value[0].trim(), value[1].trim());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        String propertiesFilePath = "F:\\国际化后端\\海外国际化-英语.xlsx";
        BufferedReader reader = new BufferedReader(new FileReader(propertiesFilePath));
        String read;
        List<PropertiesEntity> excelData = excel.getExcelData(propertiesFilePath, PropertiesEntity.class);
        String result = replaceProperties(filePathVI);
        System.out.println(result);
        BufferedWriter writer = new BufferedWriter(new FileWriter(outFilePath));
        if (!StringUtils.isEmpty(result)) {
            writer.write(result,0, result.length());
            writer.flush();
            writer.close();
        }
    }



    public static String replaceProperties(String filePath) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String read;
        int line = 1;
        while ((read = reader.readLine()) != null) {
//            System.out.println(line ++);
            if (StringUtils.isEmpty(read)) {
                sb.append(read).append("\n");
                continue;
            }
            read = read.trim();
            if (line == 2060) {
//                System.out.println("-");
            }
            if (read.startsWith("#") || !read.contains("=")) {
                sb.append(read).append("\n");
                continue;
            }
            String[] value = read.split("=");
            String ch = cnProperties.get(value[0].trim());
            PropertiesEntity entity = excel.dataMap.get(ch);
            String viValue = "";
            if (entity != null) {
                viValue = entity.getViValue();
            }
            if (StringUtils.isEmpty(viValue)) {
                if (value.length >= 2) {
                    viValue = value[1].trim();
                }
            }
            sb.append(value[0].trim()).append("=").append(viValue).append("\n");
        }
        return sb.toString();
    }





}
