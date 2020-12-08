package com.example.wsy.io;

import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadProblemCode {

    private static List<String> needTransform = new ArrayList<>();

    private static List<String> codes = new ArrayList<>();

    static {
        needTransform.add("ProblemPackageQueryController.java");
        needTransform.add("ProblemPackagePermissionController.java");
        needTransform.add("ProblemPackageHandlingController.java");
        needTransform.add("ProblemPackageController.java");
        needTransform.add("ProblemPackageHandingService.java");
                needTransform.add("ProblemPackagePermissionService.java");
        needTransform.add("ProblemPackageQueryService.java");
                needTransform.add("ProblemPackageService.java");


    }


    public static String outFileName = "F:\\国际化后端";

    private static Pattern patternChinese = Pattern.compile("DataResultResp\\.(success|fail)\\(\"(.*)\"\\)");

    public static void main(String[] args) throws IOException {
//        String pathName = "F:\\workspace-n\\station-module-exp\\src\\main\\java\\cn\\com\\yto56\\station\\module\\exp";
        String pathName = "E:\\operate-new\\ytg-operate\\ytg-operate-backend\\src\\main\\java\\com\\ytoglobal";

//        File file = new File(pathName);
//        String[] list = file.list();
        File dir = new File(pathName);
        String str = getFileData(dir);
        if (!StringUtils.isEmpty(str)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName + "\\problem.txt"));
            writer.write(str, 0, str.length());
            writer.flush();
            writer.close();
            System.out.println("文件写入成功!");
        } else {
            System.out.println("无文件生成!");
        }

        if (codes.size() > 0) {
            for (String code : codes) {
                System.out.println(code);
            }
        }

    }

    private static String getFileData(File file) throws IOException {
        StringBuilder sb1 = new StringBuilder();
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            if (fileNames != null && fileNames.length > 0) {
                for (String fileName : fileNames) {
                    File subFile = new File(file.getPath() + "\\" + fileName);
                    if (!needTransform.contains(fileName) && subFile.isFile()) {
                        continue;
                    }
                    String context = getFileData(subFile);
                    if (!StringUtils.isEmpty(context)) {
                        sb1.append(context);
                    }
                }
            }
        }else {
            BufferedReader in = new BufferedReader(new FileReader(file));
//          Pattern patternData = Pattern.compile("([A-Z_0-9]+ *=.*\")");
            String  read;
            boolean isNote;
            boolean nextIsNote = false;
            int i = 0;
            while ((read = in.readLine()) != null) {
                i ++;
                read = read.trim();
                if (read.contains("return DataResultResp.")) {
                    Matcher matcher = patternChinese.matcher(read);
                    while (matcher.find()) {
                        String code = matcher.group(2);
                        sb1.append("line ").append(i).append(": ").append(code).append("\n");
                        if(!codes.contains(code)) {
                            codes.add(code);
                        }
                    }
                }
            }
            if (sb1.length() > 0){
                sb1.insert(0, file.getName() + "\n");
                sb1.append("\n").append("\n").append("\n");
            }
        }
        return sb1.toString();
    }


}

