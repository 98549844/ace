package com.restController;

import com.service.RedisService;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname: RedisController
 * @Date: 6/5/2021 12:17 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/redis")
@Api(tags = "redis")
public class RedisRestController {
    private final static Logger log = LogManager.getLogger(RedisRestController.class.getName());


    private final RedisService redisService;

    @Autowired
    public RedisRestController(RedisService redisService) {
        this.redisService = redisService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String test() {
        redisService.set("ace", "<<< ace >>>");
        redisService.set("version", "版本 2.5", 10);
        System.out.println(redisService.get("ace"));
        System.out.println(redisService.get("version"));

        return redisService.get("ace") + ":" + redisService.get("version");
    }


}

