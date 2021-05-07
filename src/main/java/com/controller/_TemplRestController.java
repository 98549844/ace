package com.controller;

import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname: _TemplController
 * @Date: 4/5/2021 10:20 下午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/template")
@Api(tags = "template")
public class _TemplRestController {
    private static Logger log = LogManager.getLogger(_TemplRestController.class.getName());

    public static void main(String[] args) {


    }

}

