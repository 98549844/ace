package com.ace.restController;

import com.ace.exception.ResponseException;
import com.ace.models.common.RespResult;
import com.ace.models.entity.Users;
import com.ace.service.RedisService;
import com.ace.service.UsersService;
import com.ace.utilities.FastJson2Util;
import com.ace.utilities.NullUtil;
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
    public RespResult match(@PathVariable(value = "key") String key) {
        List<String> result = redisService.scan(key);
        return RespResult.success(result);
    }

    @Operation(summary = "Get all")
    @RequestMapping(method = RequestMethod.GET, value = "/getAll")
    public RespResult getAll() {
        Set<String> keys = redisService.getKeys();
        Map<String, Object> result = new HashMap<>();
        for (String key : keys) {
            Object obj = redisService.get(key);
            result.put(key, FastJson2Util.ObjectToJson(obj)); // object 转换成json
        }
        return RespResult.success(result);
    }


    @Operation(summary = "Check key exist")
    @RequestMapping(method = RequestMethod.GET, value = "/exist/{key}")
    public RespResult exist(@PathVariable(value = "key") String key) {
        return RespResult.success(redisService.keyExists(key));
    }

    @Operation(summary = "Delete", description = "xxx for single, using ',' for split, xxx,xxx,xxx... for multi !!!")
    @RequestMapping(method = RequestMethod.GET, value = "/delete")
    public RespResult delete(@RequestParam(value = "key") String... key) {
        log.info("key: {}", (Object) key);
        redisService.delete(key);
        return RespResult.success(true);
    }


    @Operation(summary = "Get value by key")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{key}")
    public RespResult getValueByKey(@PathVariable(value = "key") String key) {
        //  return AjaxResponse.success(redisService.get(key));
        Object obj = redisService.get(key);
        String result = FastJson2Util.ObjectToJson(obj);
        System.out.println(result);
        return RespResult.success(result);
    }

    @Operation(summary = "Get hash by key")
    @RequestMapping(method = RequestMethod.GET, value = "/getHash/{key}")
    public RespResult getHashByKey(@PathVariable(value = "key") String key) {
        Map<Object, Object> obj = redisService.hashGet(key);
        String result = FastJson2Util.ObjectToJson(obj);
        System.out.println(result);
        return RespResult.success(result);
    }

    @Operation(summary = "Get type by key")
    @RequestMapping(method = RequestMethod.GET, value = "/getType/{key}")
    public RespResult getTypeByKey(@PathVariable(value = "key") String key) {
        DataType type = redisService.getType(key);
        Map result = new HashMap();
        result.put("key", key);
        result.put("type", type.name());
        return RespResult.success(result);
    }

    @Operation(summary = "Get all types")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllTypes")
    public RespResult getTypes() {
        Set<String> keys = redisService.getKeys();
        Map result = new HashMap();
        for (String key : keys) {
            DataType type = redisService.getType(key);
            result.put("key=>" + key, "type:" + type.name());
        }
        return RespResult.success(result);
    }


    @Operation(summary = "Get all keys")
    @RequestMapping(method = RequestMethod.GET, value = "/getKeys")
    public RespResult getKeys() {
        Set<String> keys = redisService.getKeys();
        return RespResult.success(keys);
    }


    @Operation(summary = "Set key value")
    @RequestMapping(method = RequestMethod.GET, value = "/set/{key}/{value}")
    public RespResult set(@PathVariable String key, @PathVariable String value) {
        return RespResult.success(redisService.set(key, value));
    }

    @Operation(summary = "Set key value using post method")
    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public RespResult post(@RequestParam("key") String key, @RequestParam("body") Object body) {
        return RespResult.success(redisService.set(key, body));
    }

    @Operation(summary = "Rename key")
    @RequestMapping(value = "/renameKey/{oldKey}/{newKey}", method = RequestMethod.PUT)
    public RespResult renameKey(@PathVariable String oldKey, @PathVariable String newKey) {
        boolean result = redisService.renameKey(oldKey, newKey);
        if (result) {
            return RespResult.success(true);
        } else {
            return RespResult.error(new ResponseException("rename key exist, use other key"));
        }
    }


    @Operation(summary = "Clear all ")
    @RequestMapping(method = RequestMethod.GET, value = "/clearAll")
    public RespResult clearAll() {
        return RespResult.success(redisService.clearAll());
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
    public RespResult getUsersByRedis() {
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

        return RespResult.success(ls);
    }

    @Operation(summary = "redis connection status")
    @RequestMapping(method = RequestMethod.GET, value = "/getConnection")
    public RespResult connection() {
        String status;
        if (redisService.getConnection()) {
            status = "closed";
        } else {
            status = "connected";
        }
        String connection = "Redis connection status: " + status;
        return RespResult.success(connection);
    }

}

