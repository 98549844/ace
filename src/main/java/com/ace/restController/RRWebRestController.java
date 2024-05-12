package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.utilities.FileUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @Classname: RRWebRestController
 * @Date: 11/5/24 PM3:30
 * @Author: garlam
 * @Description:
 */

//https://www.cnblogs.com/pijunqi/p/14480346.html
//https://blog.csdn.net/blackcat88/article/details/88972515
@RestController
@RequestMapping("/rest/rrweb")
@Tag(name = "RRWeb")
public class RRWebRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(RRWebRestController.class.getName());

    //  StringBuilder tmp = new StringBuilder();
    int count = 1;
    List<String> tmp = new ArrayList<>();

    @Operation(summary = "保存记录")
    @RequestMapping(method = RequestMethod.POST, value = "/save.html")
    public AjaxResponse save(@RequestBody String data) {
        tmp.add(data);

        String p = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/restController/aaa/";
        String f = count + ".json";
        FileUtil.write(p, f, data, false);
        ++count;
        return AjaxResponse.success(true);
    }



    @Operation(summary = "回放")
    @RequestMapping(method = RequestMethod.GET, value = "/playback/{playbackId}")
    public AjaxResponse playback(@PathVariable String playbackId) throws IOException {
        log.info("access RRWeb playbackId: {}", playbackId);

        StringBuilder data = new StringBuilder();
        for (String s : tmp) {
            String trimmed = s.trim();
            if (trimmed.length() > 2) {
                String sub = trimmed.substring(1, trimmed.length() - 1) + ',';
                data.append(sub);
            }
        }

        String result = "[" + data.substring(0, data.length() - 1) + "]";

        String p = "/Users/garlam/IdeaProjects/ace/src/main/java/com/ace/restController/aaa/";
        String seven = "playback.json";

        FileUtil.rewrite(p + seven, result);
        return AjaxResponse.success(result);
        //  return tmp;
    }

    @Operation(summary = "回放列表")
    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public AjaxResponse getPlaybackList() {
        return AjaxResponse.success(true);
    }

}


