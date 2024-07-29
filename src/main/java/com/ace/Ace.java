package com.ace;

import com.ace.generator.InsertUsers;
import com.ace.models.entity.Users;
import com.ace.utilities.FastJson2Util;
import com.ace.utilities.GsonUtil;
import com.ace.utils.CangJieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace extends CangJieUtil {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws Exception {
      //  checkCangJieCode(null);

        List<Users> user = InsertUsers.insertUsers();

        System.out.println(FastJson2Util.formatJson(FastJson2Util.toJson(user)));
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

