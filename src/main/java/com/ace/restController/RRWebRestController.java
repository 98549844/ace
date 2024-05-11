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
        //Map<String, Object> data ;
        // System.out.println("token:  " + data.get("token"));
        // System.out.println("body:  " + data.get("body"));
        dataList.add(data);
        tmp = data;
        System.out.println(data);
        if (dataList.size() > 100) {
            dataList = new ArrayList<>();
        }

        //System.out.println(data);
        //String[] dataArr = data.split("&");
        //token = dataArr[0].split("=");
        //body = dataArr[1].split("=");
        //dataMap.put(token[0], token[1]);
        //dataMap.put(body[0], body[1]);
        //dataList.add(body[1]);
        //System.out.println("body: " + body[1]);

        return AjaxResponse.success(data);
    }

    //@Operation(summary = "保存记录")
    //@RequestMapping(method = RequestMethod.POST, value = "/save.html")
    //public AjaxResponse save(@RequestBody Map<String, Object> data) {
    //    //Map<String, Object> data ;
    //    // System.out.println("token:  " + data.get("token"));
    //    // System.out.println("body:  " + data.get("body"));
    //    dataList.add(data.get("body").toString());
    //    if (dataList.size() > 100) {
    //        dataList = new ArrayList<>();
    //    }
    //
    //    //System.out.println(data);
    //    //String[] dataArr = data.split("&");
    //    //token = dataArr[0].split("=");
    //    //body = dataArr[1].split("=");
    //    //dataMap.put(token[0], token[1]);
    //    //dataMap.put(body[0], body[1]);
    //    //dataList.add(body[1]);
    //    //System.out.println("body: " + body[1]);
    //
    //    return AjaxResponse.success(data.get("body").toString());
    //}

    @Operation(summary = "回放")
    @RequestMapping(method = RequestMethod.GET, value = "/playback/{playbackId}")
    public String playback(@PathVariable String playbackId) {
        log.info("access rrweb playbackId: {}", playbackId);
        // System.out.println(dataMap.get(body[0]));
        StringBuilder sb = new StringBuilder();
        for (String s : dataList) {
            sb.append(s);
        }
        System.out.println(sb);
        //  return AjaxResponse.success(sb.toString());
        return tmp;
    }

    @Operation(summary = "回放列表")
    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public AjaxResponse getPlaybackList() {

        return AjaxResponse.success(true);
    }

}


