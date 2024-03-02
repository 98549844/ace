package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.NotificationRequest;
import com.ace.service.NotificationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * @Classname: NotificationRestController
 * @Date: 1/3/2024 9:59 am
 * @Author: garlam
 * @Description:
 */

@RestController
public class NotificationRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(NotificationRestController.class.getName());
    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping("/sendNotification")
    public String sendNotification(@RequestBody NotificationRequest request) {
        try {
            return notificationService.sendNotification(request.getTitle(), request.getBody(), request.getToken());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error sending notification.";
        }
    }
}

