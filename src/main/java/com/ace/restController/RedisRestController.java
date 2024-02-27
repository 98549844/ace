package com.ace.restController;

import com.ace.exception.ResponseException;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import com.ace.service.RedisService;
import com.ace.service.UsersService;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.util.FastJson2Util;
import com.util.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Classname: RedisController
 * @Date: 6/5/2021 12:17 上午
 * @Author: garlam
 * @Description:
 */
@RestController
@RequestMapping("/rest/redis")
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


    @Operation(summary = "Match key", description = "* for match symbol")
    @RequestMapping(method = RequestMethod.GET, value = "/match/{key}")
    public AjaxResponse match(@PathVariable(value = "key") String key) {
        List<String> result = redisService.scan(key);
        return AjaxResponse.success(result);
    }

    @Operation(summary = "Get all")
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public AjaxResponse getAll() {
        Set<String> keys = redisService.getKeys();
        Map<String, Object> result = new HashMap<>();
        for (String key : keys) {
            Object obj = redisService.get(key);
            result.put(key, FastJson2Util.ObjectToJson(obj)); // object 转换成json
        }
        return AjaxResponse.success(result);
    }


    @Operation(summary = "Check key exist")
    @RequestMapping(method = RequestMethod.GET, value = "/exist/{key}")
    public AjaxResponse exist(@PathVariable(value = "key") String key) {
        return AjaxResponse.success(redisService.keyExists(key));
    }

    @Operation(summary = "Delete", description = "xxx for single, using ',' for split, xxx,xxx,xxx... for multi !!!")
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public AjaxResponse delete(@RequestParam(value = "key") String... key) {
        log.info("key: {}", (Object) key);
        redisService.delete(key);
        return AjaxResponse.success(true);
    }


    @Operation(summary = "Get value by key")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{key}")
    public AjaxResponse getValueByKey(@PathVariable(value = "key") String key) {
        //  return AjaxResponse.success(redisService.get(key));
        Object obj = redisService.get(key);
        String result = FastJson2Util.ObjectToJson(obj);
        System.out.println(result);
        return AjaxResponse.success(result);
    }

    @Operation(summary = "Get hash by key")
    @RequestMapping(method = RequestMethod.GET, value = "/getHash/{key}")
    public AjaxResponse getHashByKey(@PathVariable(value = "key") String key) {
        Map<Object, Object> obj = redisService.hashGet(key);
        String result = FastJson2Util.ObjectToJson(obj);
        System.out.println(result);
        return AjaxResponse.success(result);
    }

    @Operation(summary = "Get type by key")
    @RequestMapping(method = RequestMethod.GET, value = "/getType/{key}")
    public AjaxResponse getTypeByKey(@PathVariable(value = "key") String key) {
        DataType type = redisService.getType(key);
        Map result = new HashMap();
        result.put("key", key);
        result.put("type", type.name());
        return AjaxResponse.success(result);
    }

    @Operation(summary = "Get all types")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllTypes")
    public AjaxResponse getTypes() {
        Set<String> keys = redisService.getKeys();
        Map result = new HashMap();
        for (String key : keys) {
            DataType type = redisService.getType(key);
            result.put("key=>" + key, "type:" + type.name());
        }
        return AjaxResponse.success(result);
    }


    @Operation(summary = "Get all keys")
    @RequestMapping(method = RequestMethod.GET, value = "/getKeys")
    public AjaxResponse getKeys() {
        Set<String> keys = redisService.getKeys();
        return AjaxResponse.success(keys);
    }


    @Operation(summary = "Set key value")
    @RequestMapping(method = RequestMethod.GET, value = "/set/{key}/{value}")
    public AjaxResponse set(@PathVariable String key, @PathVariable String value) {
        return AjaxResponse.success(redisService.set(key, value));
    }

    @Operation(summary = "Set key value using post method")
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public AjaxResponse post(@RequestParam("key") String key, @RequestParam("body") Object body) {
        return AjaxResponse.success(redisService.set(key, body));
    }

    @Operation(summary = "Rename key")
    @RequestMapping(value = "/renameKey/{oldKey}/{newKey}", method = RequestMethod.PUT)
    public AjaxResponse renameKey(@PathVariable String oldKey, @PathVariable String newKey) {
        boolean result = redisService.renameKey(oldKey, newKey);
        if (result) {
            return AjaxResponse.success(true);
        } else {
            return AjaxResponse.error(new ResponseException("rename key exist, use other key"));
        }
    }


    @Operation(summary = "Clear all ")
    @RequestMapping(method = RequestMethod.GET, value = "/clearAll")
    public AjaxResponse clearAll() {
        return AjaxResponse.success(redisService.clearAll());
    }

    @Operation(summary = "GetVersion")
    @RequestMapping(method = RequestMethod.GET, value = "/getVersion")
    public String getVersion() {
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
            redisService.set("all", FastJson2Util.ObjectToJson(users));
        }

        Users user = usersService.findByUserAccount("garlam");
        redisService.set(user.getUsername(), FastJson2Util.ObjectToJson(user));

        List<Object> ls = new ArrayList<>();
        ls.add(redisService.get(user.getUsername()));
        ls.add(redisService.get("all"));

        return AjaxResponse.success(ls);
    }

    @Operation(summary = "redis connection status")
    @RequestMapping(method = RequestMethod.GET, value = "/getConnection")
    public AjaxResponse connection() {
        String status;
        if (redisService.getConnection()) {
            status = "closed";
        } else {
            status = "connected";
        }
        String connection = "Redis connection status: " + status;
        return AjaxResponse.success(connection);
    }

}

