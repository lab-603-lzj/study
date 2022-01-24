package com.example.poi.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;

/**
 * @Description:
 * @author: lzj
 * @date: 2021年11月29日 14:06
 */
public class WordUtil {
    public static void main(String[] args) {
        if ((6 & 1) == 0){
            System.out.println("true");
        }
    }

    public XWPFDocument createXWPFDocument(String absolutePath){
        File file = new File(absolutePath);
        if (!file.exists()){
            boolean mkdirs = file.mkdirs();
        }
        XWPFDocument doc = new XWPFDocument();
        return doc;
    }
}
