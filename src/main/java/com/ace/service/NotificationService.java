package com.ace.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import static com.google.firebase.messaging.Message.builder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

/**
 * @Classname: NotificationService
 * @Date: 1/3/2024 9:09 am
 * @Author: garlam
 * @Description:
 */

@Service
public class NotificationService  {
    private static final Logger log = LogManager.getLogger(NotificationService.class.getName());
    public String sendNotification(String title, String body, String token) throws InterruptedException, ExecutionException {

        Notification notification =  Notification.builder()
                                    .setTitle("Ace Application ")
                                    .setBody("Ace Application Notification !!!")
                                    .build();

        Message message = Message.builder()
                .setToken(token)
                .setNotification(notification)
                .build();

        String response = FirebaseMessaging.getInstance().sendAsync(message).get();

        return response;
    }

}
