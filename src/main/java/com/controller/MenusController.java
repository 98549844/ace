package com.controller;

import com.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname: MenuController
 * @Date: 12/11/2021 11:45 下午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MenusController extends CommonController {
    private static Logger log = LogManager.getLogger(MenusController.class.getName());


}

