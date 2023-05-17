package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import com.ace.service.CacheMapService;
import com.ace.service.UsersService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
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

    private UsersService usersService;
    private CacheMapService cacheMapService;

    public CacheMapRestController(CacheMapService cacheMapService, UsersService usersService) {
        this.usersService = usersService;
        this.cacheMapService = cacheMapService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/setMap")
    public AjaxResponse setMap() {
        List<Users> ls = usersService.findAll();
        Map map = new HashMap();
        map.put("users", ls);
        cacheMapService.setMap(map);
        return AjaxResponse.success(true);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/getCacheMap")
    public AjaxResponse getMap() {
        Map map = cacheMapService.getMap();
        return AjaxResponse.success(map);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/{key}")
    public Object get(@PathVariable String key) {
        String result = cacheMapService.getString(key);

        return AjaxResponse.success(result);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/put/{key}/{value}")
    public AjaxResponse put(@PathVariable String key, @PathVariable String value) {

        cacheMapService.put(key, value);
        String result = cacheMapService.getString(key);

        return AjaxResponse.success(result);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/delete/{key}")
    public AjaxResponse delete(@PathVariable String key) {
        cacheMapService.delete(key);
        String result = cacheMapService.getString(key);

        return AjaxResponse.success(result);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/flush")
    public AjaxResponse flush() {
        cacheMapService.flush();
        return AjaxResponse.success(true);

    }

}

