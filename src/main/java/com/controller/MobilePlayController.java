package com.controller;

import com.constant.AceEnvironment;
import com.controller.common.CommonController;
import com.service.MobilePlayService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * @Classname: DplayerController
 * @Date: 2022/11/12 下午 10:11
 * @Author: kalam_au
 * @Description:
 */

@Controller
@RequestMapping("/ace")
public class MobilePlayController extends CommonController {
    private static final Logger log = LogManager.getLogger(MobilePlayController.class.getName());

    private final MobilePlayService mobilePlayService;
    private final String videoPath;

    @Autowired
    public MobilePlayController(MobilePlayService mobilePlayService) {
        this.mobilePlayService = mobilePlayService;
        this.videoPath = AceEnvironment.getVideoPath();
    }


    //http://localhost:8088/ace/stream/mp4/bbb

    @RequestMapping(value = "/stream/play.html", method = RequestMethod.GET)
    @ResponseBody
    public Mono<ResponseEntity<byte[]>> mobilePlay(@RequestParam(value = "playId") String playId, @RequestHeader(value = "Range", required = false) String httpRangeList) {
        log.info("access stream/play.html/{}", playId);
        String device = getDevice();
        log.info("device type: {}", device);

        String fileName = "bbb";
        String fileType = "mp4";
        String videoFile = videoPath+fileName;

        return Mono.just(mobilePlayService.prepareContent(fileName, fileType, httpRangeList));

    }
}

