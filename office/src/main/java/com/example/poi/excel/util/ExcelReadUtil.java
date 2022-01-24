package com.example.poi.excel.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Logger;

/**
 * 主要包括
 * 读取特定行数据、读取特定列数据、读取特定单元格数据、
 * 有头读取、无头读取 并转化为对象list
 * 打开excel
 */
/**
 * @Description: excel 读取功能工具类
 * @author: lzj
 * @date: 2021年10月11日 13:27
 */
public class ExcelReadUtil {

    private static Logger log = Logger.getLogger(ExcelReadUtil.class.getName());

    /**
     * 获取工作空间
     * @param input
     * @return
     * @throws IOException
     */
    public static Workbook getWorkbook(InputStream input) {
        Workbook wb = null;
        try {
            wb = WorkbookFactory.create(input);
        }catch (IOException ioe){
            log.info("获取workbook失败");
        }
        return wb;
    }

    /**
     * 获取所在sheet
     * @param wb
     * @param i
     * @return
     */
    public static Sheet getSheet(Workbook wb,int i){
        return wb.getSheetAt(i);
    }

    /**
     * 获取所在sheet
     * @param input
     * @param i
     * @return
     */
    public static Sheet getSheet(InputStream input,int i){
        return getWorkbook(input).getSheetAt(i);
    }

    /**
     * 获取单元格内容
     * @param sheet
     * @param row 如果是合并的单元格，填首行地址
     * @param col 如果是合并的单元格，填首列地址
     * @return 内容
     */
    public static Cell getCell(Sheet sheet,int row,int col){
        ;
        return sheet.getRow(row).getCell(col);
    }

    /**
     * 获取表标题
     * @param sheet
     * @param row 如果是合并的单元格，填首行地址
     * @param col 如果是合并的单元格，填首列地址
     * @return
     */
    public static String getTitle(Sheet sheet,int row,int col){
        return getCell(sheet, row, col).getStringCellValue();
    }

    /**
     * 获取表标题
     * @param sheet
     * @return
     */
    public static String getTitle(Sheet sheet){
        return getTitle(sheet,0,0);
    }

    public static Object getCellValue(Cell cell){
        Object ret = null;
        switch (Objects.requireNonNull(cell.getCellType())){
            case BLANK: ret = "";break;
            case STRING: ret = cell.getStringCellValue();break;
            case BOOLEAN: ret = cell.getBooleanCellValue();break;
            case FORMULA: ret = cell.getCellFormula();break;
            case NUMERIC: ret = cell.getNumericCellValue();break;
            case ERROR:
            case _NONE:
            default:
        }
        return ret;
    }

    /**
     * 获取特定行，行与数据顺序相同
     * @param sheet
     * @param row
     * @param clazz
     * @param colStart
     * @param <T>
     * @return
     */
    public static <T> T getRow(Sheet sheet, int row, Class<T> clazz,int colStart){


        Row sheetRow = sheet.getRow(row);
        int cellNum = sheetRow.getPhysicalNumberOfCells();
        Field[] fields = clazz.getDeclaredFields();


        // 将变量跟对应的值对应起来
        LinkedHashMap<String,Object> map = new LinkedHashMap<>();
        for (int i = 0,j = 0; i < cellNum && j<fields.length; i++) {
            if (i>colStart-1) {
                map.put(fields[j].getName(), getCellValue(sheetRow.getCell(i)));
                j++;
            }
        }

        // 转化为不定类型
        JSONObject json = JSON.parseObject(JSON.toJSONString(map));

        return JSONObject.toJavaObject(json, clazz);
    }

    /**
     * 获取行数据，默认行与数据顺序相同
     * @param sheet
     * @param row
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getRow(Sheet sheet, int row, Class<T> clazz){
        return getRow(sheet,row,clazz,0);
    }

    /**
     * 获取列数据 todo 返回值可以按照字段类型进行返回
     * @param sheet
     * @param col
     * @param startRow
     * @return
     */
    public static List<String> getCol(Sheet sheet,int col,int startRow){
        int lastRow = sheet.getLastRowNum();
        List<String> result = new ArrayList<>();
        if (lastRow == 0){
            return new ArrayList<>();
        }
        // 从选定的一行到末尾
        for (int i = startRow; i <= lastRow; i++) {
            result.add(getCell(sheet,i,col).getStringCellValue());
        }

        return result;
    }

    public static List<List<String>> getData(Sheet sheet,int colStart,int rowStart){
        int lastRow = sheet.getLastRowNum();
        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i <= lastRow; i++) {

        }
        return null;
    }
}
