package com.ace.controller;

import com.ace.controller.common.CommonController;
import com.ace.service.PdfService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Classname: PdfController
 * @Date: 2023/9/22 下午 12:00
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/pdf/generate")
public class PdfController extends CommonController {
    private static final Logger log = LogManager.getLogger(PdfController.class.getName());

    private final PdfService pdfService;

    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/post", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] generatePdf(HttpServletResponse httpServletResponse) {
        log.info("RequestMapping 未完成 !!!");
        return pdfService.generatePdf(httpServletResponse);
    }


    //http://localhost:8088/ace/pdf/generate/getPdf.html
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/getPdf.html", produces = MediaType.APPLICATION_PDF_VALUE)
    public byte[] getPdf(HttpServletResponse httpServletResponse) {
        log.info("Generate pdf");
        return pdfService.generatePdf(httpServletResponse);
    }


    //http://localhost:8088/api/pdf/generate/getPage.html
    @RequestMapping(value = "/getPage.html", method = RequestMethod.GET)
    public ModelAndView getPage() {
        ModelAndView modelAndView = super.pageWithOutLogin("ace/modules/report/pdf/template");
        modelAndView.addObject("data", pdfService.exampleData());
        return modelAndView;
    }

}

