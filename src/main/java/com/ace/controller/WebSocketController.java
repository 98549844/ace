package com.ace.controller;

import com.ace.controller.common.CommonController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


/**
 * @Classname: WebSocketController
 * @Date: 7/4/2024 10:12 am
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class WebSocketController extends CommonController {
    private static final Logger log = LogManager.getLogger(WebSocketController.class.getName());
    //https://blog.csdn.net/qq_42402854/article/details/130948270

    /**
     * 跳转到websocketDemo.html页面，携带自定义的cid信息。
     * http://localhost:8081/ace/webSocket/{userAccount}
     *
     * @return
     */
    @GetMapping("/webSocket.html")
    public ModelAndView webSocket() {

        ModelAndView modelAndView = super.page("ace/modules/websocket/im");
        modelAndView.addObject("cid", getCurrentUser().getUserAccount());
        return modelAndView;
    }


}

