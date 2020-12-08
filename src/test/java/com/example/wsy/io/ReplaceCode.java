package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReplaceCode {

    private static Set<String> code = new HashSet<>();

    static {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("F:\\Code\\待替换.txt"));
            String read;
            while ((read = reader.readLine()) != null) {
                if (StringUtils.isEmpty(read)) {
                    continue;
                }
                read = read.trim().replaceAll(" ", "");
                String[] value = read.split("=");
                code.add(value[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final String filePath = "F:\\workspace-n\\station-module-exp\\src\\main";
    private static final String outPath = "F:\\国际化后端";

    public static void main(String[] args) throws IOException {
        File file = new File(filePath);
        String data = getFile(file);
        if (!StringUtils.isEmpty(data)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outPath + "\\Code Replace.txt"));
            writer.write(data, 0, data.length());
            writer.flush();
            writer.close();
            System.out.println("文件写入成功");
        } else {
            System.out.println("无文件生成");
        }
    }

    private static String getFile(File file) {
        StringBuilder data = new StringBuilder();
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            if (fileNames == null || fileNames.length ==0) {
                return data.toString();
            }
            for (String fileName : fileNames) {
                File subFile = new File(file.getPath() + "\\" + fileName);
                data.append(getFile(subFile));
            }
        } else {
            data.append(getFileContext(file));
        }
        return data.toString();
    }

    private static String getFileContext(File file) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            boolean isNote;
            //下一行是否被注释
            boolean nextIsNote = false;
            String str;
            int i = 0; //记录行数
            while ((str = in.readLine()) != null) {
                i++;
                isNote = nextIsNote;
                String s;
                if ((s = str.trim()).contains("*/") && isNote) {
                    s = s.replaceAll(".*\\*/", "");
                    nextIsNote = false;
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                    isNote = false;
                }
                if (s.contains("//")) {
                    s = s.replaceAll("//.*", "");
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                }
                if (s.contains("/*") && s.contains("*/")) {
                    s = s.replaceAll("/\\*.*\\*/", "");
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                }
                if (s.contains("/*")) {
                    s = s.replaceAll("/\\*.*", "");
                    nextIsNote = true;
                    if (StringUtils.isEmpty(s)) {
                        continue;
                    }
                }


                if (!isNote) {
                    if (s.contains("OverseasCode") || s.contains("ExceptionCode")) {
                        for (String value : code) {
                            String text;
                            if (s.contains(text = value)) {
                                sb.append("line: ").append(i).append(" 行, ").append(text).append("\n");
                            }
                        }
                    }
                }
            }
            if (sb.length() > 0) {
                sb.insert(0, file.getName() + "\n");
                sb.append("\n").append("\n").append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
