package com.ace.controller;

import com.ace.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: QrCodeController
 * @Date: 13/7/24 PM11:59
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class QrCodeController extends CommonController {
    private static final Logger log = LogManager.getLogger(QrCodeController.class.getName());

    @RequestMapping(value = "/qrcode.html", method = RequestMethod.GET)
    public ModelAndView introduction() {
        ModelAndView modelAndView = super.page("ace/modules/qrcode/index");
        return modelAndView;
    }
}
