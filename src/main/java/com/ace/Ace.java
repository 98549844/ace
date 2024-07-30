package com.ace;

import com.ace.utils.CangJieUtil;
import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;
import com.spire.pdf.PdfPageBase;
import com.spire.pdf.texts.PdfTextExtractOptions;
import com.spire.pdf.texts.PdfTextExtractor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.spire.pdf.graphics.fonts.PdfUsedFont;

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
        //获取文档中的所有字体
        PdfUsedFont[] fonts = doc.getUsedFonts();



        //doc.saveToFile(excelPath, FileFormat.XLSX);

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


/*    import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfDictionary;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfArray;
import java.util.List;
import java.util.ArrayList;

    public class FontExtractor {
        public static List<String> extractFonts(String pdfPath) throws Exception {
            List<String> fonts = new ArrayList<>();
            PdfReader reader = new PdfReader(pdfPath);

            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                PdfDictionary pageDict = reader.getPageN(i);
                PdfDictionary resDict = pageDict.getAsDict(PdfName.RESOURCES);
                PdfDictionary fontDict = resDict.getAsDict(PdfName.FONT);

                for (PdfName name : fontDict.getKeys()) {
                    PdfDictionary font = fontDict.getAsDict(name);
                    PdfName subType = font.getAsName(PdfName.SUBTYPE);
                    if (subType != null) {
                        fonts.add(subType.toString());
                    }
                }
            }

            return fonts;
        }

        public static void main(String[] args) {
            try {
                List<String> fonts = extractFonts("path/to/your/pdf/file.pdf");
                for (String font : fonts) {
                    System.out.println(font);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

}

