package com.ace;

import com.spire.pdf.PdfDocument;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import com.spire.pdf.FileFormat;
/**
 * @Classname: PDF
 * @Date: 4/8/24 AM12:21
 * @Author: garlam
 * @Description:
 */


public class PDF {
    private static final Logger log = LogManager.getLogger(PDF.class.getName());

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/195290.pdf";
        String filePath1 = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/196455.pdf";
        String outputExcel = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/result.xlsx";
        //readPDFBoxV3(filePath1);
        toExcel(filePath1, outputExcel);
    }

    public static void toExcel(String filePath, String outputExcel){
        PdfDocument pdfDocument = new PdfDocument();
        pdfDocument.loadFromFile(filePath);
        pdfDocument.saveToFile(outputExcel, FileFormat.XLSX);

    }


    public static void readPDFBoxV3(String filePath) throws IOException {
        // 1、加载指定PDF文档
        PDDocument document = org.apache.pdfbox.Loader.loadPDF(new File(filePath));
        // 2、创建文本提取对象
        PDFTextStripper stripper = new PDFTextStripper();
        // 3、获取指定页面的文本内容
        String text = stripper.getText(document);
        System.out.println("获取文本内容: " + text);
        // 4、关闭
        document.close();

    }
}

