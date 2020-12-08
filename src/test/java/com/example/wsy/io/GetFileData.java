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

public class GetFileData {

    private static List<String> needTransform = new ArrayList<>();

    static {
        needTransform.add("AirMainNumberMaintainController.java");
        needTransform.add("ArrivalSignQueryController.java");
        needTransform.add("ClearOrApplyCustomsIncomeQueryController.java");
        needTransform.add("ClearOrApplyCustomsIssuedQueryController.java");
        needTransform.add("CustomsClearanceDeliveryMaintainController.java");
        needTransform.add("DeliveryQueryController.java");
        needTransform.add("GetBagScanQueryController.java");
        needTransform.add("OrderAnalysisedQueryController.java");

        needTransform.add("PickUpQueryController.java");
        needTransform.add("ProblenPackageQueryController.java");
        needTransform.add("PutInCarCancelQueryController.java");
        needTransform.add("PutInCarQueryController.java");
        needTransform.add("PutOutCarQueryController.java");
        needTransform.add("RealnameVerificationQueryController.java");
        needTransform.add("ReturnQueryController.java");
        needTransform.add("SeizedScanQueryController.java");
        needTransform.add("SendQueryController.java");
        needTransform.add("SetBagScanQueryController.java");
        needTransform.add("ShipmentDataQueryController.java");
        needTransform.add("ShipmentsQueryController.java");
        needTransform.add("SignQueryController.java");
        needTransform.add("WalkQueryController.java");
        needTransform.add("AirMainNumberMaintainServiceImpl.java");
//        needTransform.add("WalkTrackController.java");
//        needTransform.add("WaybillManagementController.java");
//        needTransform.add("WaybillQueryController.java");

//        needTransform.add("ArrivalOrderDaoImpl.java");
//        needTransform.add("BatchManagementInfoDaoImpl.java");
//        needTransform.add("DeliveryScanDaoImpl.java");
//        needTransform.add("DestinationBasedataDaoimpl.java");
//        needTransform.add("IncomeIssueDaoImpl.java");
        needTransform.add("OrderDaoImpl.java");
//        needTransform.add("WalkTrackDaoImpl.java");

//        needTransform.add("BagScanServiceImpl.java");
//        needTransform.add("BatchManagementInfoServiceImpl.java");
//        needTransform.add("LabelPrintValidate.java");
//        needTransform.add("ProblemPackageServiceImpl.java");
//        needTransform.add("TaxesServiceImpl.java");
//        needTransform.add("WaybillManageServiceImpl.java");

//        needTransform.add("ExcelUtil.java");

    }


    public static String outFileName = "F:\\国际化后端";

    private static Pattern patternChinese = Pattern.compile("[\u4e00-\u9fa5]+");

    public static void main(String[] args) throws IOException {
//        String pathName = "F:\\workspace-n\\station-module-exp\\src\\main\\java\\cn\\com\\yto56\\station\\module\\exp";
        String pathName = "F:\\workspace-n\\station-module-exp\\src\\main\\scanQuery\\cn\\com\\yto56\\station\\module\\scanQuery";

//        File file = new File(pathName);
//        String[] list = file.list();
        File dir = new File(pathName);
        String str = getFileData(dir);
        if (!StringUtils.isEmpty(str)) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outFileName + "\\scanQuery1.txt"));
            writer.write(str, 0, str.length());
            writer.flush();
            writer.close();
            System.out.println("文件写入成功!");
        } else {
            System.out.println("无文件生成!");
        }

    }

    private static String getFileData(File file) throws IOException {
        StringBuilder sb1 = new StringBuilder();
        if (file.isDirectory()) {
            String[] fileNames = file.list();
            if (fileNames != null && fileNames.length > 0) {
                for (String fileName : fileNames) {
                    File subFile = new File(file.getPath() + "\\" + fileName);
//                    if (!needTransform.contains(fileName) && subFile.isFile()) {
//                        continue;
//                    }
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
                isNote = nextIsNote;
                if (read.startsWith("logger.") || read.startsWith("log.") || read.startsWith("System.out.print")) {
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
                    while (matcher.find()) {
                        sb1.append("line ").append(i).append(": ").append(matcher.group()).append("\n");
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
