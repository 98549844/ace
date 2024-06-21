package com.ace.utils;

import com.ace.utilities.NullUtil;
import com.ace.utilities.RandomUtil;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: PdfUtil
 * @Date: 2023/9/26 上午 09:55
 * @Author: kalam_au
 * @Description:
 */


public class PdfUtil {
    private static final Logger log = LogManager.getLogger(PdfUtil.class.getName());

    public static void main(String[] args) throws IOException {
        PdfUtil pdfUtil = new PdfUtil();
        try {
            List<InputStream> pdfs = new ArrayList<InputStream>();
            pdfs.add(new FileInputStream("c:\\aa.pdf"));
            pdfs.add(new FileInputStream("c:\\bb.pdf"));
            OutputStream output = new FileOutputStream("c:\\merge.pdf");
            pdfUtil.concatPDFs(pdfs, output, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /** 多份pdf串连成一份pdf
     * @param streamOfPdfFiles
     * @param outputStream
     * @param pagination
     */
    public void concatPDFs(List<InputStream> streamOfPdfFiles, OutputStream outputStream, boolean pagination) {
        Document document = new Document();
        try {
            List<InputStream> pdfs = streamOfPdfFiles;
            List<PdfReader> readers = new ArrayList<>();
            int totalPages = 0;

            // Create Readers for the pdfs.
            for (InputStream pdf : pdfs) {
                PdfReader pdfReader = new PdfReader(pdf);
                readers.add(pdfReader);
                totalPages += pdfReader.getNumberOfPages();
            }
            // Create a writer for the outputStream
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);

            document.open();
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA,
                    BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            PdfContentByte cb = writer.getDirectContent(); // Holds the PDF

            PdfImportedPage page;
            int currentPageNumber = 0;
            int pageOfCurrentReaderPDF = 0;

            // Loop through the PDF files and add to the output.
            for (PdfReader pdfReader : readers) {
                // Create a new page in the target for each source page.
                while (pageOfCurrentReaderPDF < pdfReader.getNumberOfPages()) {
                    document.newPage();
                    pageOfCurrentReaderPDF++;
                    currentPageNumber++;
                    page = writer.getImportedPage(pdfReader,
                            pageOfCurrentReaderPDF);
                    cb.addTemplate(page, 0, 0);
                    // Code for pagination.
                    if (pagination) {
                        cb.beginText();
                        cb.setFontAndSize(bf, 9);
                        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, ""
                                        + currentPageNumber + " of " + totalPages, 520,
                                5, 0);
                        cb.endText();
                    }
                }
                pageOfCurrentReaderPDF = 0;
            }
            outputStream.flush();
            document.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (document.isOpen())
                document.close();
            try {
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * 读取url的html然后render html to pdf
     *
     * @param targetUrl
     * @param pdfLocation
     * @throws IOException
     * @throws DocumentException
     */
    public static void generatePdfFromUrl(String targetUrl, String pdfLocation) throws IOException, DocumentException {
        OutputStream outputStream = new FileOutputStream(pdfLocation);
        HtmlUtil htmlUtil = new HtmlUtil();
        String html = htmlUtil.getHtmlFromUrl(targetUrl);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlUtil.convertToXHtml(html));
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/WINDOWS/Fonts/SIMSUN.TTC", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 支持一张或多张图片转换成pdf
     *
     * @param input
     * @param output
     * @throws IOException
     */
    public static void toPdf(String input, String output) throws IOException {
        if (NullUtil.isNull(input) || NullUtil.isNull(output)) {
            log.error("Input param is null");
            return;
        }

        File f = new File(input);
        if (f.exists()) {
            if (new File(output).isDirectory()) {
                output = output + "pdf_ver_" + RandomUtil.getInt(100) + ".pdf";
            }

            if (f.isDirectory()) {
                jpgsMergeToPdf(input, output);
            } else {
                jpgToPdf(input, output);
            }
        }

    }


    /**
     * 使用pdfbox将jpg转成pdf
     *
     * @param input   jpg输入流
     * @param pdfPath pdf文件存储路径
     * @throws IOException IOException
     */
    private static void jpgToPdf(String input, String pdfPath) throws IOException {

        InputStream jpgStream = new FileInputStream(input);

        PDDocument pdDocument = new PDDocument();
        BufferedImage image = ImageIO.read(jpgStream);
        PDPage pdPage = new PDPage(new PDRectangle(image.getWidth(), image.getHeight()));
        pdDocument.addPage(pdPage);
        PDImageXObject pdImageXObject = LosslessFactory.createFromImage(pdDocument, image);
        PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);
        contentStream.drawImage(pdImageXObject, 0, 0, image.getWidth(), image.getHeight());
        contentStream.close();
        pdDocument.save(pdfPath);
        pdDocument.close();
    }


    /**
     * 文件夹下多张图片转成一个pdf(多页文件)
     *
     * @param imageFolderPath
     * @param pdfPath
     */
    private static void jpgsMergeToPdf(String imageFolderPath, String pdfPath) {
        try {
            // 图片文件夹地址
            // 图片地址
            String imagePath;
            // PDF文件保存地址
            // 输入流
            FileOutputStream fos = new FileOutputStream(pdfPath);
            // 创建文档
            Document doc = new Document(null, 0, 0, 0, 0);
            //doc.open();
            // 写入PDF文档
            PdfWriter.getInstance(doc, fos);
            // 读取图片流
            BufferedImage img;
            // 实例化图片
            Image image;
            // 获取图片文件夹对象
            File file = new File(imageFolderPath);
            File[] files = file.listFiles();
            // 循环获取图片文件夹内的图片
            for (File f : files) {
                if (f.getName().endsWith(".png") || f.getName().endsWith(".jpg") || f.getName().endsWith(".gif") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".tif")) {
                    System.out.println(f.getName());
                    imagePath = imageFolderPath + f.getName();
                    System.out.println(f.getName());
                    // 读取图片流
                    img = ImageIO.read(new FileInputStream(imagePath));
                    doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                    // 根据图片大小设置文档大小
                    doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
                    // 实例化图片
                    image = Image.getInstance(imagePath);
                    // 添加图片到文档
                    doc.open();
                    doc.add(image);
                }
            }
            // 关闭文档
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

