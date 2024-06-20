package com.ace.restController;

import com.ace.models.common.RespResult;
import com.ace.utilities.FastJson2Util;
import com.ace.utilities.FileUtil;
import com.ace.utilities.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.*;

/**
 * @Classname: WinHanverkyRestController
 * @Date: 6/20/2024 10:57 AM
 * @Author: garlam.au
 * @Description:
 */


@RestController
@RequestMapping("/rest/winhanvery/tools")
@Tag(name = "winhanvery-tools")
public class WinHanverkyRestController {
    private static final Logger log = LogManager.getLogger(WinHanverkyRestController.class.getName());

    final static String KEYS = "KEYS *";

    @Operation(summary = "get All Keys", description = "* for match symbol <br>" + "password: #R%diS@li%356* / #R%diS@li%356*UaT <br>" + "uat internet host: r-3nsqfv3t3vt73sw531.redis.rds.aliyuncs.com <br>" + "uat outernet host: r-3nsqfv3t3vt73sw531pd.redis.rds.aliyuncs.com  <br>" + "prod internet host: r-3nse6ueaxcd8hjmyf0.redis.rds.aliyuncs.com <br>" + "prod outernet host: r-3nse6ueaxcd8hjmyf0pd.redis.rds.aliyuncs.com <br>")
    @RequestMapping(method = RequestMethod.GET, value = "/getAllKeys")
    public RespResult getAllKeys(@RequestParam(value = "host", required = false) String host, @RequestParam(value = "port", required = false) Integer port, @RequestParam(value = "password", required = false) String password) {
        host = host == null ? "localhost" : host;
        port = port == null ? 6379 : port;

        //outer net
        Jedis jedis = new Jedis(host, port);
        // 认证，如果需要的话
        if (NullUtil.isNonNull(password)) {
            jedis.auth(password);
        }
        jedis.connect();
        if(!jedis.isConnected()){
            return RespResult.success("Redis connection fail !");
        }
        Set<String> keys = jedis.keys("*");
        List<String> result = new ArrayList<>();
        for (String k : keys) {
            result.add("key:" + k + "  " + "type:" + jedis.type(k));
        }
        return RespResult.success(result);
    }

    @Operation(summary = "get Redis KeyValue", description = "* for match symbol <br>" + "password: #R%diS@li%356* / #R%diS@li%356*UaT <br>" + "uat internet host: r-3nsqfv3t3vt73sw531.redis.rds.aliyuncs.com <br>" + "uat outernet host: r-3nsqfv3t3vt73sw531pd.redis.rds.aliyuncs.com  <br>" + "prod internet host: r-3nse6ueaxcd8hjmyf0.redis.rds.aliyuncs.com <br>" + "prod outernet host: r-3nse6ueaxcd8hjmyf0pd.redis.rds.aliyuncs.com <br>"+"output result save in \\ \\H018FE0100519\\ace\\misc\\result.txt <br>")
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public RespResult getKeysValues(@RequestParam(value = "host", required = false) String host, @RequestParam(value = "port", required = false) Integer port, @RequestParam(value = "password", required = false) String password, @RequestParam(value = "key", required = false) String key, @RequestParam(value = "output", required = false) boolean output) throws Exception {
        host = host == null ? "localhost" : host;
        port = port == null ? 6379 : port;
        key = key == null ? KEYS : key;

        //outer net
        Jedis jedis = new Jedis(host, port);
        // 认证，如果需要的话
        if (NullUtil.isNonNull(password)) {
            jedis.auth(password);
        }
        jedis.connect();
        if(!jedis.isConnected()){
            return RespResult.success("Redis connection fail !");
        }

        Map result = new LinkedHashMap();
        result.put("host", host);
        result.put("port", port);
        result.put("password", password);
        result.put("connection", jedis.isConnected());

        if (KEYS.equals(key)) {
            Set<String> keys = jedis.keys("*");
            System.out.println(keys);
            Map map = new LinkedHashMap();
            for (String k : keys) {
                map.put(k, FastJson2Util.ObjectToJson(getValue(jedis, k, jedis.type(k))));
            }
            result.put("keys", keys);
            result.put("result", map);

        } else {
            result.put("key", key);
            result.put("value", FastJson2Util.ObjectToJson(getValue(jedis, key, jedis.type(key))));
        }

        // 关闭连接
        jedis.close();

        if (output) {
            String fileName = "result.txt";
            String filePath = "/opt/workspace/ace/misc/";
            FileUtil.write(filePath, fileName, result.get("result"), false);
        }
        return RespResult.success(result);
    }


    final static String STRING = "string";
    final static String HASH = "hash";
    final static String LIST = "list";
    final static String SET = "set";
    //final static String
    final static String STREAM = "stream";

    private Object getValue(Jedis jedis, String key, String type) throws Exception {
        System.out.println("key: " + key + " type: " + type);
        return switch (type) {
            case STRING -> jedis.get(key);
            case HASH -> jedis.hgetAll(key); //二維空間數組
            case LIST -> jedis.lrange(key, 0, -1);
            // 第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
            case SET -> jedis.smembers(key);
            case STREAM -> "STREAM沒有處理";
            default -> new Object();
        };

    }

//if value is of type string -> GET <key>
//if value is of type hash -> HGET or HMGET or HGETALL <key>
//if value is of type lists -> lrange <key> <start> <end>
//if value is of type sets -> smembers <key>
//if value is of type sorted sets -> ZRANGEBYSCORE <key> <min> <max>
//if value is of type stream -> xread count <count> streams <key> <ID>. https://redis.io/commands/xread
}

