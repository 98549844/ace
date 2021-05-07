package com.controller;

import com.service.RedisService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname: RedisController
 * @Date: 6/5/2021 12:17 上午
 * @Author: garlam
 * @Description:
 */

@Controller
@RequestMapping("/redis")
@Api(tags = "redis")
public class RedisRestController {
    private static Logger log = LogManager.getLogger(RedisRestController.class.getName());

    @Autowired
    private RedisService redisService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String test() {
        redisService.set("ace", "<<< ace >>>");
        redisService.set("version", "版本 2.5", 10);
        System.out.println(redisService.get("ace"));
        System.out.println(redisService.get("version"));

        return redisService.get("ace") + ":" + redisService.get("version");
    }


}

