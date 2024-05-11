package com.ace.restController;

import com.ace.exception.ResponseException;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.AccessLog;
import com.ace.utilities.FastJson2Util;
import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Classname: RRWebRestController
 * @Date: 11/5/24 PM3:30
 * @Author: garlam
 * @Description:
 */


//https://blog.csdn.net/blackcat88/article/details/88972515
@RestController
@RequestMapping("/rest/rrweb")
@Tag(name = "RRWeb")
public class RRWebRestController {
    private static final Logger log = LogManager.getLogger(RRWebRestController.class.getName());

    Map<String, String> dataMap = new HashMap<>();
    List<String> dataList = new ArrayList<>();
    String[] token;
    String[] body;
    String tmp;

    @Operation(summary = "保存记录")
    @RequestMapping(method = RequestMethod.POST, value = "/save.html")
    public AjaxResponse save(@RequestBody String data) {
        System.out.println("save data: " + !data.isEmpty());
        tmp = data;
        return AjaxResponse.success(data);
    }


    @Operation(summary = "回放")
    @RequestMapping(method = RequestMethod.GET, value = "/playback/{playbackId}")
    public String playback(@PathVariable String playbackId) {
        log.info("access RRWeb playbackId: {}", playbackId);
        // return AjaxResponse.success(tmp);
        return tmp;
    }

    @Operation(summary = "回放列表")
    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public AjaxResponse getPlaybackList() {
        return AjaxResponse.success(true);
    }

}


