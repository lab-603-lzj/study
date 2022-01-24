package com.example.poi.pdf.pdfbox;

import com.example.poi.pdf.itext.util.other.FileType;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

/**
 * @Description:
 * @author: lzj  只能转化文字部分，且没有固定的格式
 * @date: 2021年11月29日 11:11
 */
public class PdfReplace {

    public static void main(String[] args) throws IOException {
        String pdfPath = System.getProperty("user.dir")+File.separator+"navicat"+ FileType.PDF;
        pdf2word(pdfPath);
    }

    public static void pdf2word(String absolutePath) throws IOException {
        PDDocument doc = PDDocument.load(new File(absolutePath));
        int numberOfPages = doc.getNumberOfPages();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("."));
        String fileName = absolutePath + ".doc";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fos = new FileOutputStream(fileName);
        Writer writer = new OutputStreamWriter(fos, "UTF-8");
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(true);// 排序
        stripper.setStartPage(1);// 设置转换的开始页
        stripper.setEndPage(numberOfPages);// 设置转换的结束页
        stripper.writeText(doc, writer);
        writer.close();
        doc.close();
        System.out.println("pdf转换word成功！");
    }
}
