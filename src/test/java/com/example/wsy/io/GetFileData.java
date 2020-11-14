package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetFileData {


    public static String outFileName = "F:\\ExceptionCode.txt";

    public static void main(String[] args) throws IOException {
        String pathName = "F:\\workspace-n\\station-commons\\src\\main\\java\\cn\\com\\yto56\\station\\commons\\stationin\\cgsys\\commons\\";
//        File file = new File(pathName);
//        String[] list = file.list();
        String fileName = pathName + "\\ExceptionCode.java";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        Pattern patternChinese = Pattern.compile("[\u4e00-\u9fa5]+[\\w\u4e00-\u9fa5() ，,.!?/%^&*_]*");
        Pattern patternData = Pattern.compile("([A-Z_0-9]+ *=.*\")");
        String  read;
        boolean isNote = false;
        boolean nextIsNote = false;
        BufferedWriter out = new BufferedWriter(new FileWriter(outFileName));
        while ((read = in.readLine()) != null) {
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
                    Matcher matcherData = patternData.matcher(read);
                    if (matcherData.find()) {
                        out.write(matcherData.group(1), 0, matcherData.group(1).length());
                        out.newLine();
                    }

                }
            }
        }
        out.flush();
        out.close();
    }
}
