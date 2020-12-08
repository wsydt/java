package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogContainChineseFile {
    private static final String filePath = "F:\\workspace-n\\station-module-exp\\src\\main\\java\\cn\\com\\yto56\\station\\module\\exp";

    private static final String outWrite = "F:\\replace\\zzzzzzzzzz重新检查\\";

    private static List<String> logFile = Arrays.asList("putOutCarScanJS.jsp", "waybillPrint.jsp");

    //使用正则表达式匹配替换文件内容
    private static String patternCh = "([\u4e00-\u9fa5]+[\\w\u4e00-\u9fa5() ，。,.!?/%^&*_]*)";
    private static Pattern patternChinese = Pattern.compile(patternCh);

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        List<String> unReplaced = new ArrayList<>(Arrays.asList("",""));
        String dir = "permission\\";
        String subDir = "utilsJS\\";
        getDirectData(new File(filePath + dir), false);


        BufferedWriter writer = new BufferedWriter(new FileWriter(outWrite +"checkPermission.txt"));
        writer.write(sb.toString(), 0, sb.length());
        writer.flush();
        writer.close();

        if (unReplaced.size() > 2) {
            System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^&&&&&&&&&&&&&&&&&&&&&&&&&&&&^&^&^&^&^&^&^&^&^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            for (String un : unReplaced) {
                System.out.println(un);
            }
        } else {
            System.out.println("complete!!!!!!!!!!!!!!!!!!!!");
        }

    }

    private static void getDirectData(File fileDir, boolean isJS) {
        if (fileDir.isDirectory()) {
            String[] names;
            if ((names = fileDir.list()) != null) {
                for (String fileName : names) {
                    File dir = new File(fileDir + "\\" + fileName);
                    if (dir.isDirectory()) {
                        getDirectData(dir,true);
                    } else {
                        sb.append("start.......................------------------------\n");
                        sb.append(fileName).append("\n");
                        fileName = fileDir + "\\" + fileName;
                        getFileContext(fileName, isJS, patternChinese);
                        sb.append("end.......................------------------------\n").append("\n").append("\n");
                    }
                }
            }
        }
    }

    public static void getFileContext(String fileName, boolean isJS, Pattern patternChinese) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            boolean isNote;
            //下一行是否被注释
            boolean nextIsNote = false;
            String str;
            int i = 0; //记录行数
            while ((str = in.readLine()) != null) {
                i++;
                isNote = nextIsNote;
                String s;
                int startIndex;
                if (isJS) {
                    if (((s = str.trim()).contains("--%>") || s.contains("*/")) && isNote) {
                        s = s.replaceAll(".*[(--%>)|*/]", "");
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
                    if ((s.contains("<%--") || s.contains("/*")) && (s.contains("--%>")) || s.contains("*/")) {
                        s = s.replaceAll("[<%--|/*].*[(--%>)|*/]", "");
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }
                    }
                    if (s.contains("<%--") || s.contains("/*")) {
                        s = s.replaceAll("[<%--|/*].*", "");
                        nextIsNote = true;
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }
                    }
                    if (!StringUtils.isEmpty(s)) {
                        String html = s.replaceAll("< */ *script *>", "");
                        if (StringUtils.isEmpty(html)) {
                            isJS = false;
                        }
                    }
                } else {
                    //判断当前行 是以注释结尾的 去掉注释部分
                    if (((s = str.trim()).contains("--%>") || s.contains("-->")) && isNote) {
                        s = s.replaceAll(".*--%?>", "");
                        nextIsNote = false;
                        //如果当前行为空, 则读取下一行
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }
                        isNote = false;
                    }
                    //判断当前行中存在的注释, 删除本行注释
                    if (((startIndex = s.indexOf("<!--")) > -1 || (startIndex = s.indexOf("<%--")) > -1)
                            && (s.lastIndexOf("-->") > startIndex || s.lastIndexOf("--%>") > startIndex)) {
                        s = s.replaceAll("<[!--|%--].*--%?>", "");
                        //如果当前行为空, 则读取下一行
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }

                    }
                    if (startIndex > -1) {
                        s = s.replaceAll("<[!--|%--]+.*", "");
                        nextIsNote = true;
                        //如果当前行为空, 则读取下一行
                        if (StringUtils.isEmpty(s)) {
                            continue;
                        }
                    }
                    if (!StringUtils.isEmpty(s)) {
                        String js = s.replaceAll("<script +type *= *\"text/javascript\" *>", "");
                        if (StringUtils.isEmpty(js)) {
                            isJS = true;
                        }
                    }
                }

                if (!isNote) {
                    Matcher matcherChinese = patternChinese.matcher(s);
                    while (matcherChinese.find()) {
                        String value = matcherChinese.group(1).trim();
                        sb.append("第").append(i).append(" 行 : ").append(value).append("\n");
                    }
                }
            }

            System.out.println("--------------------------------------------------------------end------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
