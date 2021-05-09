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


@RestController
public class ExceptionController extends CommonController {
    private Log log = LogFactory.getLog(this.getClass());

   // @RequestMapping(value = {"/error"})
    public ModelAndView error() {
        log.info(super.getRequest().getRequestURL());
        ModelAndView modelAndView;
        int status = super.getResponse().getStatus();
        if (status == 404) {
            log.info("ERROR CODE : " + status);
            modelAndView = super.page("ace/error404");
        } else if (status == 500) {
            log.info("ERROR CODE : " + status);
            modelAndView = super.page("ace/error500");
        } else {
            log.info("ERROR CODE : " + status);
            modelAndView = super.page("ace/error");
        }
        return modelAndView;
    }
}