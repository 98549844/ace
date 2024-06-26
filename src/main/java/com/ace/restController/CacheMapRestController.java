package com.ace.restController;

import com.ace.models.common.RespResult;
import com.ace.service.CacheMapService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname: CacheMapRestController
 * @Date: 2023/5/17 下午 04:42
 * @Author: kalam_au
 * @Description:
 */

@RestController
@RequestMapping("/rest/cacheMap")
@Tag(name = "CacheMap")
public class CacheMapRestController {
    private static final Logger log = LogManager.getLogger(CacheMapRestController.class.getName());

    private final CacheMapService cacheMapService;

    public CacheMapRestController(CacheMapService cacheMapService) {
        this.cacheMapService = cacheMapService;
    }

    @Operation(summary = "set")
    @RequestMapping(method = RequestMethod.GET, value = "/set/{key}/{value}")
    public RespResult set(@PathVariable String key, @PathVariable String value) {
        Map map = new HashMap();
        map.put(key, value);
        cacheMapService.setMap(map);
        return RespResult.success(true);
    }


    @Operation(summary = "getMap")
    @RequestMapping(method = RequestMethod.GET, value = "/getCacheMap")
    public RespResult getMap() {
        Map map = cacheMapService.getMap();
        return RespResult.success(map);
    }

    @Operation(summary = "get")
    @RequestMapping(method = RequestMethod.GET, value = "/get/{key}")
    public Object get(@PathVariable String key) {
        String result = cacheMapService.getString(key);

        return RespResult.success(result);

    }

    @Operation(summary = "put")
    @RequestMapping(method = RequestMethod.GET, value = "/put/{key}/{value}")
    public RespResult put(@PathVariable String key, @PathVariable String value) {

        cacheMapService.put(key, value);
        String result = cacheMapService.getString(key);

        return RespResult.success(result);

    }

    @Operation(summary = "delete")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{key}")
    public RespResult delete(@PathVariable String key) {
        cacheMapService.delete(key);
        String result = cacheMapService.getString(key);

        return RespResult.success(result);
    }

    @Operation(summary = "flush")
    @RequestMapping(method = RequestMethod.GET, value = "/flush")
    public RespResult flush() {
        cacheMapService.flush();
        return RespResult.success(true);

    }

}

