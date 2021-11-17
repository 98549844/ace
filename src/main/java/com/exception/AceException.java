package com.exception;

import com.controller.common.CommonController;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Scanner;


@RestController
public class AceException extends CommonController implements ErrorController {
    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping(value = {"/error"})
    @ResponseBody
    public ModelAndView error() {
        log.info(super.getRequest().getRequestURL());
        ModelAndView modelAndView;
        int status = super.getResponse().getStatus();
        if (status == 404) {
            log.info("error code: "+ status);
            modelAndView = super.page("ace/error404");
        } else if (status == 500){
            log.info("error code: "+ status);
            modelAndView = super.page("ace/error500");
        } else{
            log.info("error code: "+ status);
            modelAndView = super.page("ace/error");
        }
        return modelAndView;
    }

}