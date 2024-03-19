package com.ace.restController;

import com.ace.util.SseEmitterServer;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

/**
 * @Classname: SseEmitterRestController
 * @Date: 19/3/2024 11:19 pm
 * @Author: garlam
 * @Description:
 */
@CrossOrigin(origins = "http://192.168.1.100:8088")
@RestController
@RequestMapping("/rest/SseEmitter")
@Tag(name = "Server Site Event")
public class SseEmitterRestController {
    private static final Logger log = LogManager.getLogger(SseEmitterRestController.class.getName());




    /**
     * 用于创建连接
     */
    @Operation(summary = "count")
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterServer.connect(userId);
    }

    /**
     * 发送给单个人
     *
     */
    @Operation(summary = "发送消息")
    @PostMapping("/sendUser")
    public ResponseEntity<String> sendUser(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        String message = param.get("message");

        SseEmitterServer.sendMessage(userId, message);
        return ResponseEntity.ok("SSE推送消息给" + userId);
    }


    /**
     * 推送给所有人
     *
     * @return
     */
    @Operation(summary = "发送给所有人")
    @PostMapping("/sendAllUser")
    public ResponseEntity<String> sendAllUser(@RequestBody Map<String, String> param) {
        String message = param.get("message");

        SseEmitterServer.batchSendMessage(message);
        return ResponseEntity.ok("SSE推送消息给所有人");
    }


    /**
     * 关闭连接
     */
    @Operation(summary = "关闭连接")
    @GetMapping("/close/{userId}")
    public ResponseEntity<String> close(@PathVariable("userId") String userId) {
        SseEmitterServer.removeUser(userId);
        return ResponseEntity.ok("连接关闭");
    }
}

