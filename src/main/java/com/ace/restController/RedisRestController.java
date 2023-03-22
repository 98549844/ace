package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import com.ace.service.RedisService;
import com.ace.service.UsersService;
import com.util.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Classname: RedisController
 * @Date: 6/5/2021 12:17 上午
 * @Author: garlam
 * @Description:
 */
@RestController
@RequestMapping("/rest/redis")
//@Api(tags = "redis")
@Tag(name = "Redis")
public class RedisRestController {
    private final static Logger log = LogManager.getLogger(RedisRestController.class.getName());


    private final RedisService redisService;
    private final UsersService usersService;

    @Autowired
    public RedisRestController(RedisService redisService, UsersService usersService) {
        this.redisService = redisService;
        this.usersService = usersService;
    }


    @Operation(summary = "Get by key")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{key}")
    public AjaxResponse getByKey(@PathVariable(value = "key") String key) {
        return AjaxResponse.success(redisService.get(key));
    }

    @Operation(summary = "Get key set")
    @RequestMapping(method = RequestMethod.GET, value = "/getKeys")
    public AjaxResponse getKeys() {
        Set<String> keys = redisService.getKeys();
        return AjaxResponse.success(keys);
    }


    @Operation(summary = "Clear redis cache")
    @RequestMapping(method = RequestMethod.GET, value = "/clearAll")
    public AjaxResponse clearAll() {
        return AjaxResponse.success(redisService.clearAll());
    }

    @Operation(summary = "Get")
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public String get() {
        redisService.set("ace", "<<< ace >>>");
        redisService.set("version", "版本 3.0", 10);
        System.out.println(redisService.get("ace"));
        System.out.println(redisService.get("version"));

        return redisService.get("ace") + ":" + redisService.get("version");
    }


    @Operation(summary = "Get users by redis")
    @RequestMapping(method = RequestMethod.GET, value = "/getUsersByRedis")
    public AjaxResponse getUsersByRedis() {
        Object object = redisService.get("all");
        if (NullUtil.isNull(object)) {
            List<Users> users = usersService.findAll();
            List<String> result = new ArrayList<>();
            for (Users user : users) {
                String u = user.getUsername() + "   [" + user.getPassword() + "]" + "   [" + user.getEmail() + "]";
                result.add(u);
            }
            redisService.set("all", result);
            // redisService.set("all", new Gson().toJson(users)); //gson 拿不到private final field, 问题未处理
        }

        // Users user = usersService.findByUserAccount("garlam");
        // redisService.set(user.getUsername(), new Gson().toJson(user));

        List<Object> ls = new ArrayList<>();
        ls.add(redisService.get("all"));
        // ls.add(redisService.get(user.getUsername()));

        return AjaxResponse.success(ls);
    }

}

