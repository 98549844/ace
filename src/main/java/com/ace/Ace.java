package com.ace;

import com.ace.utils.CangJieUtil;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextExtractOptions;
import com.spire.pdf.texts.PdfTextExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace extends CangJieUtil {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws Exception {
        String p = "C:\\Users\\Garlam.Au\\Downloads\\a1.pdf";
        String excelPath = "C:\\Users\\Garlam.Au\\Downloads\\aa.xlsx";

        PdfDocument doc = new PdfDocument();
        //加载PDF文档
        doc.loadFromFile(p);
        doc.saveToFile(excelPath, FileFormat.XLSX);

        // 获取第一页，遍历文档所有页便可提取文档所有文本内容
        PdfPageBase page = doc.getPages().get(0);
        // 创建PdfTextExtractor 对象
        PdfTextExtractor textExtractor = new PdfTextExtractor(page);

        // 创建PdfTextExtractOptions 对象
        PdfTextExtractOptions extractOptions = new PdfTextExtractOptions();

        // 从页面中提取文本
        String text = textExtractor.extract(extractOptions);

        System.out.println(text);

    }


}

