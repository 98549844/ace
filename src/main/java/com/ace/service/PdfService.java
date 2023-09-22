package com.ace.service;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;

import static com.ace.constant.Constant.UTF_8;

/**
 * @Classname: PdfService
 * @Date: 2023/9/22 下午 12:06
 * @Author: kalam_au
 * @Description:
 */

@Service
public class PdfService {
    private static final Logger log = LogManager.getLogger(PdfService.class.getName());

    private final ClassLoaderTemplateResolver classLoaderTemplateResolver;


    public PdfService(ClassLoaderTemplateResolver classLoaderTemplateResolver) {
        this.classLoaderTemplateResolver = classLoaderTemplateResolver;
    }


    public byte[] generatePdf(HttpServletResponse httpServletResponse) {
        log.info("generate pdf run........!");
        byte[] pdf = null;
        log.info("Initializing .......!");
        TemplateEngine templateEngine = new TemplateEngine();
        try {
            templateEngine.setTemplateResolver(classLoaderTemplateResolver);
            Context context = new Context();
            context.setVariable("data", exampleData());
            String renderHtmlContent = templateEngine.process("templates/ace/modules/report/pdf/template.html", context);
            String xHtml = convertToXHtml(renderHtmlContent);

            //String fileName = sampleReq.getFullName()+"_"+System.currentTimeMillis();
            String fileName = String.valueOf(System.currentTimeMillis());
            File output = new File(fileName + ".pdf");
            log.info("initializing output path : {}", output.getPath());
            ITextRenderer iTextRenderer = new ITextRenderer();
            iTextRenderer.setDocumentFromString(xHtml);
            iTextRenderer.layout();

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            iTextRenderer.createPDF(outputStream);
            pdf = outputStream.toByteArray();
            outputStream.close();
            httpServletResponse.setContentType("application/pdf");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + ".pdf\"");
            httpServletResponse.setHeader("Content-Filename", fileName + ".pdf");
            return pdf;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pdf;
    }

    private String convertToXHtml(String renderHtmlContent) {
        log.info("convert to xhtml......!");
        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(renderHtmlContent.getBytes(StandardCharsets.UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(StandardCharsets.UTF_8);
    }


    public Data exampleData() {
        Data data = new Data();
        data.setFirstname("Garlam");
        data.setLastname("Au");
        data.setStreet("Street one");
        data.setZipCode("87548744");
        data.setCity("Hong Kong City , China");
        return data;
    }

    static class Data {
        private String firstname;
        private String lastname;
        private String street;
        private String zipCode;
        private String city;

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }
    }
}

