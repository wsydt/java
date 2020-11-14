package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {

    public static String outFileName = "E:\\国际化文件\\渠道单号批量更改.txt";

    public static void main(String[] args) throws IOException {
        String name = "quickScan";
        boolean queryFlag = false;
        processData(name, queryFlag);


    }

    private static void processData(String name,boolean queryFlag) throws IOException {
        String filePath = "F:\\workspace-n\\station-web-exp\\src\\main\\webapp\\WEB-INF\\pages\\";
        String dir = "scan";
        String JSDir = dir + "\\utilsJS";
//        String name = "incomeIssueQuery";
        if (queryFlag) {
            dir += "Query";
            JSDir = dir + "\\" + dir + "JS" ;
        }
        String fileName = filePath + dir + "\\" + name;

        Set<Object> jspPropertiesCode = new HashSet<>(30);
        Set<Object> jspChineseName = new HashSet<>(30);
        ;
        Set<Object> jsChineseName = new HashSet<>(30);
        Set<Object> jsPropertiesCode = new HashSet<>(30);

        getFileData(fileName + ".jsp", false, jspPropertiesCode, jspChineseName);
        System.out.println("\n\n------------------------------------------------------文件信息分隔符-------------------------------------------------------\n\n");
        fileName = filePath + JSDir + "\\" + name + "JS.jsp";
//        fileName = filePath + dir + "\\" + name + ".jsp";
        getFileData(fileName, true, jsPropertiesCode, jsChineseName);

        jspPropertiesCode.addAll(jsPropertiesCode);
        jspChineseName.addAll(jsChineseName);


        System.out.println("\n\n\n\n\n\n\n\n\n\n-------------------------------------------------------------- result ------------------------------------------------");

        for (Object obj : jspPropertiesCode) {
            System.out.println(obj);
        }
        System.out.println(jspPropertiesCode.size());
        System.out.println("-------------------------------------------------------------- split ------------------------------------------------");
        for (Object obj : jspChineseName) {
            System.out.println(obj);
        }
        System.out.println(jspChineseName.size());


        String outName = outFileName;
        BufferedWriter out = new BufferedWriter(new FileWriter(outName));
        for (Object obj : jspPropertiesCode) {
            String value = (String) obj;
            out.write(value, 0, value.length());
            out.newLine();
        }
        out.flush();
        out.close();
        System.out.println("文件写入成功");
    }

    private static void getFileData(String fileName, boolean isJS, Set<Object> setPropertiesCode, Set<Object> setChineseName) {
        Pattern pattern = Pattern.compile("<c:out +value *= *['|\"]\\$\\{([a-zA-Z0-9]*)}['|\"] */ *>");
        Pattern patternMessage = Pattern.compile("<spring:message +code *= *['\"]([a-zA-Z0-9]*)['\"] *(arguments=.*)? */? *>");
        Pattern patternChinese = Pattern.compile("[\u4e00-\u9fa5]+[\\w\u4e00-\u9fa5() ，,.!?/%^&*_]*");
        Pattern patternScript = Pattern.compile("<script +type += +\"text/javascript\" +>");

//        Set<Object> setPropertiesCode = new HashSet<>(30);
//        Set<Object> setChineseName = new HashSet<>(30);
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            boolean isNote = false;
            //下一行是否被注释
            boolean nextIsNote = false;
            String str;
            while ((str = in.readLine()) != null) {
                isNote = nextIsNote;
                String s;
                int startIndex, endIndex;
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
                            && ((endIndex = s.lastIndexOf("-->")) > startIndex || (endIndex = s.lastIndexOf("--%>")) > startIndex)) {
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
                    Matcher matcher = pattern.matcher(s);
                    while (matcher.find()) {
                        setPropertiesCode.add(matcher.group(1));
                    }
                    Matcher matcherMessage = patternMessage.matcher(s);
                    while (matcherMessage.find()) {
                        setPropertiesCode.add(matcherMessage.group(1));
                    }
                    Matcher matcherChinese = patternChinese.matcher(s);
                    while (matcherChinese.find()) {
                        setChineseName.add(matcherChinese.group());
                    }
                    System.out.println(s);
                }
            }
            System.out.println("--------------------------------------------------------------end------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Object obj : setPropertiesCode) {
            System.out.println(obj);
        }
        System.out.println(setPropertiesCode.size());
        System.out.println("-------------------------------------------------------------- split ------------------------------------------------");
        for (Object obj : setChineseName) {
            System.out.println(obj);
        }
        System.out.println(setChineseName.size());



    }

}
