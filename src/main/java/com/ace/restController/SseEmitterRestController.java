package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.PushMessage;
import com.ace.models.entity.Users;
import com.ace.service.NotificationService;
import com.ace.util.SseEmitterServer;
import com.util.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@Tag(name = "SSE Server Site Event")
public class SseEmitterRestController {
    private static final Logger log = LogManager.getLogger(SseEmitterRestController.class.getName());


    private NotificationService notificationService;

    @Autowired
    public SseEmitterRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    /**
     * 用于创建连接
     */
    @Operation(summary = "count")
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterServer.connect(userId);
    }

    @Operation(summary = "连接sse后发送消息")
    @GetMapping("/connectAfterSend/{userId}")
    public AjaxResponse connectAfterSend(@PathVariable String userId) {
        log.info("access connectAfterSend");
        List<PushMessage> pushMessages = notificationService.findByReceiver(userId);
        List<Map<String, String>> pushMessageList = new ArrayList<>();
        if (pushMessages.isEmpty()) {
            return AjaxResponse.success(pushMessageList);
        }
        for (PushMessage pm : pushMessages) {
            Map<String, String> param = new HashMap<>();
            param.put("userId", pm.getReceiver());
            param.put("message", pm.getContent());
            pushMessageList.add(param);
        }
        notificationService.deleteByReceiver(userId);

        return AjaxResponse.success(pushMessageList);
    }


    /**
     * 发送给单个人
     */
    @Operation(summary = "发送消息", description = "{ \"userId\":\"2\", \"message\":\"Ace Application\" }")
    @PostMapping("/sendUser")
    public ResponseEntity<String> sendUser(@RequestBody Map<String, String> param) {
        String userId = param.get("userId");
        String message = param.get("message");
        System.out.println("userId: " + userId + "   message: " + message);

        SseEmitter sseEmitter = SseEmitterServer.sseEmitterMap.get(userId);
        if (NullUtil.isNull(sseEmitter)) {
            PushMessage pushMessage = new PushMessage();
            pushMessage.setReceiver(userId);
            pushMessage.setContent(message);
            notificationService.save(pushMessage);
            System.out.println("message saved !");
        }

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

