package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDirList {


    public static String outFileName = "F:\\中文.txt";


    public static void main(String[] args) throws IOException {
        String pathName = "F:\\workspace-n\\station-module-exp\\src\\main\\scanQuery\\cn\\com\\yto56\\station\\module\\scanQuery\\dao\\impl\\";
        File file = new File(pathName);
        String[] list = file.list();
        Pattern patternChinese = Pattern.compile("[\u4e00-\u9fa5]+[\\w\u4e00-\u9fa5() ，,.!?/%^&*_]*");
        BufferedWriter out = new BufferedWriter(new FileWriter(outFileName));
        if (list != null) {
            for (String name : list) {
                String filePath = pathName + name;
                BufferedReader in = new BufferedReader(new FileReader(filePath));
                String read;
                int i = 0;
                out.write(name + ":", 0, name.length() + 1);
                out.newLine();
                boolean nextIsNote = false;
                boolean isNote = false;
                while ((read = in.readLine()) != null) {
                    i++;
                    read = read.trim();
                    isNote = nextIsNote;
                    if (read.startsWith("logger.") || read.startsWith("log.")) {
                        continue;
                    }
                    if (read.contains("*/") && isNote) {
                        read = read.replaceAll(".*\\*/", "");
                        nextIsNote = false;
                        if (StringUtils.isEmpty(read)) {
                            continue;
                        }
                        isNote = false;
                    }
                    if (read.contains("//")) {
                        read = read.replaceAll("//.*", "");
                        if (StringUtils.isEmpty(read)) {
                            continue;
                        }
                    }
                    if (read.contains("/*") && read.contains("*/")) {
                        read = read.replaceAll("/\\*.*\\*/", "");
                        if (StringUtils.isEmpty(read)) {
                            continue;
                        }
                    }
                    if (read.contains("/*")) {
                        read = read.replaceAll("/\\*.*", "");
                        nextIsNote = true;
                        if (StringUtils.isEmpty(read)) {
                            continue;
                        }
                    }
                    if (!isNote) {
                        Matcher matcher = patternChinese.matcher(read);
                        if (matcher.find()) {
                            out.write(String.valueOf(i), 0, String.valueOf(i).length());
                            out.newLine();
                        }
                    }
                }
                out.newLine();
                out.newLine();
                out.newLine();
                out.newLine();
                out.flush();
//                out.close();
                System.out.println("文件写入成功");

            }
            out.close();
        }
    }
}

