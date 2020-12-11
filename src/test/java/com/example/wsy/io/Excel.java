package com.example.wsy.io;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excel<T> {

    public Map<String, T> dataMap = new HashMap<>();

    public List<T> getExcelData(String filePath, Class<T> clazz) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String propertiesFilePath = "F:\\国际化后端\\海外国际化字段整理.翻译版.xlsx";
        propertiesFilePath = filePath;
        Workbook wb = readExcel(propertiesFilePath);
        List<T> list = new ArrayList<>();
        if (wb != null) {
            Sheet sheet = wb.getSheetAt(0);
            int rowNumber = sheet.getPhysicalNumberOfRows();
            Row firstRow = sheet.getRow(0);
            int column = firstRow.getPhysicalNumberOfCells();
            int line = 1;
            for (int i = 1; i < rowNumber; i++) {
//                System.out.println(line ++);
                 Row row = sheet.getRow(i);
                 if (row != null) {
                     T object = clazz.newInstance();
                     Field[] fields = object.getClass().getDeclaredFields();
                     for (Field field : fields) {
                         field.setAccessible(true);
        //                         System.out.println(field.getName());
                     }
                     Cell cellData = row.getCell(0);
        //                     propertiesEntity.setCode(cellData.getStringCellValue());
                     if (cellData != null) {
                         cellData.setCellType(CellType.STRING);
                         fields[0].set(object, cellData.getStringCellValue());
                     }
                     cellData = row.getCell(1);
        //                     propertiesEntity.setCnValue(cellData.getStringCellValue());
                     fields[1].set(object, cellData.getStringCellValue().trim());
                     cellData = row.getCell(2);
                     fields[2].set(object, cellData.getStringCellValue().trim());
                     list.add(object);
                     Method getKey = object.getClass().getDeclaredMethod("getCnValue");
                     String key = (String) getKey.invoke(object);
                     dataMap.put(key.trim(), object);
                 }
            }
        }
        return list;
    }

    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null){
            return wb;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                wb = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

}
