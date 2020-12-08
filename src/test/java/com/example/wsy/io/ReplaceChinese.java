package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceChinese {


    private static Map<String, String> map = new HashMap<>(2048);
    private static final String filePath = "F:\\workspace-n\\station-web-exp\\src\\main\\webapp\\WEB-INF\\pages\\";

    private static final String outWrite = "F:\\replace\\收入扫描\\";

    public static void main(String[] args) throws IOException {
        String name = "putOutCarScan";
        List<String> unReplaced = new ArrayList<>();
        GetMessageCode();
        boolean isQuery = false;
        //需要国际化的文件位置
        String dir = "scan";
        String subDir = "utilsJS\\";
        if (isQuery) {
            dir += "Query";
            subDir = dir + "JS\\";
        }
        String fileName = filePath + dir + "\\" + name + ".jsp";
        String fileNameJs = filePath + dir + "\\" + subDir + name + "JS.jsp";
        //使用正则表达式匹配替换文件内容
        String patternCh = "(['\"]?)[\\w&;<>]*([\u4e00-\u9fa5]+[\\w\u4e00-\u9fa5() ，。,.!?/%^&*_]*)";
        Pattern patternChinese = Pattern.compile(patternCh);
        //保存处理后的文件内容
        StringBuilder sb = new StringBuilder();

        unReplaced.add(name + ".jsp, : ");
        getFileContext(unReplaced, fileName, false, patternChinese, sb);
        //将处理后的内容写回原文件
        BufferedWriter writer = new BufferedWriter(new FileWriter(outWrite + name + ".jsp"));
        writer.write(sb.toString(), 0, sb.length() - 1);
        writer.flush();
        writer.close();

        unReplaced.add(name + "JS.jsp : ");
        getFileContext(unReplaced, fileNameJs, true, patternChinese, sb);
        //将处理后的内容写回原文件
        writer = new BufferedWriter(new FileWriter(outWrite + name + "JS.jsp"));
        writer.write(sb.toString(), 0, sb.length() - 1);
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

    private static void getFileContext(List<String> unReplaced, String fileName, boolean isJS, Pattern patternChinese, StringBuilder sb) {
        sb.setLength(0);
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            boolean isNote;
            //下一行是否被注释
            boolean nextIsNote = false;
            String str;
            while ((str = in.readLine()) != null) {
                isNote = nextIsNote;
                String s;
                int startIndex;
                if (isJS) {
                    if (((s = str.trim()).contains("--%>") || s.contains("*/")) && isNote) {
                        s = s.replaceAll(".*[(--%>)|*/]", "");
                        nextIsNote = false;
                        if (StringUtils.isEmpty(s)) {
                            sb.append(str).append("\n");
                            continue;
                        }
                        isNote = false;
                    }
                    if (s.contains("//")) {
                        s = s.replaceAll("//.*", "");
                        if (StringUtils.isEmpty(s)) {
                            sb.append(str).append("\n");
                            continue;
                        }
                    }
                    if ((s.contains("<%--") || s.contains("/*")) && (s.contains("--%>")) || s.contains("*/")) {
                        s = s.replaceAll("[<%--|/*].*[(--%>)|*/]", "");
                        if (StringUtils.isEmpty(s)) {
                            sb.append(str).append("\n");
                            continue;
                        }
                    }
                    if (s.contains("<%--") || s.contains("/*")) {
                        s = s.replaceAll("[<%--|/*].*", "");
                        nextIsNote = true;
                        if (StringUtils.isEmpty(s)) {
                            sb.append(str).append("\n");
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
                            sb.append(str).append("\n");
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
                            sb.append(str).append("\n");
                            continue;
                        }

                    }
                    if (startIndex > -1) {
                        s = s.replaceAll("<[!--|%--]+.*", "");
                        nextIsNote = true;
                        //如果当前行为空, 则读取下一行
                        if (StringUtils.isEmpty(s)) {
                            sb.append(str).append("\n");
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
                        String code;
                        if ((code = map.get(matcherChinese.group(2).trim())) != null) {
                            String replacement = "<spring:message code = '" + code + "' />";
                            if (!StringUtils.isEmpty(matcherChinese.group(1))) {
                                if ("'".equals(matcherChinese.group(1))){
                                   replacement = replacement.replaceAll("'", "\"");
                                }
                            }
                            String pattern =  matcherChinese.group(2).replaceAll("\\(","\\\\(").replaceAll("\\)", "\\\\)").replaceAll("\\?", "\\\\?");
                            str = str.replaceAll(pattern, replacement);
                        } else {
                            unReplaced.add(matcherChinese.group(2));
                            System.out.println(str);

                        }
                    }
                }
                sb.append(str).append("\n");
            }

            System.out.println("--------------------------------------------------------------end------------------------------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //获取资源文件properties
    private static void GetMessageCode() throws IOException {
        String fileName = "E:\\国际化文件\\message.txt";
        BufferedReader in = new BufferedReader(new FileReader(fileName));
        String line;
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
            map.put(data[1], data[0]);
        }
    }
}

/**
 *
 * 					<option value="首都PEK">首都PEK</option>
 * 					<option value="浦东PVG">浦东PVG</option>
 * 					<option value="广州CAN">广州CAN</option>
 * 					<option value="虹桥SHA">虹桥SHA</option>
 * 					<option value="澳门MFM">澳门MFM</option>
 * 					<option value="大兴PKX">大兴PKX</option>
 * 					<option value="深圳SZX">深圳SZX</option>
 * 					<option value="揭阳SWA">揭阳SWA</option>
 * 					<option value="香港HKG">香港HKG</option>
 * 					<option value="萧山HGH">萧山HGH</option>
 * 					<option value="南京NKG">南京NKG</option>
 * 					<option value="郑州CGO">郑州CGO</option>
 * 					<option value="宁波NGB">宁波NGB</option>
 * 					<option value="厦门XMN">厦门XMN</option>
 * 					<option value="福州FOC">福州FOC</option>
 *
 *
 *
 */
