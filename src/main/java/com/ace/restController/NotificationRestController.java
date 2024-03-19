package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.PushMessage;
import com.ace.service.NotificationService;
import com.util.RandomUtil;
import com.util.SleepUtil;
import com.util.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: NotificationRestController
 * @Date: 1/3/2024 9:59 am
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/notification")
@Tag(name = "Notification")
public class NotificationRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(NotificationRestController.class.getName());
    private final NotificationService notificationService;

    public NotificationRestController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Operation(summary = "Generate Sample Data")
    @RequestMapping(method = RequestMethod.GET, value = "/generate.html")
    public AjaxResponse generateSampleDate() {
        System.out.println("push message generating");
        List<PushMessage> messages = new ArrayList<>();
        for (int i = 0; i < 5000; i++) {
            PushMessage pm = new PushMessage();
            pm.setContent("Content=>  " + UUID.get());
            pm.setDeleted(RandomUtil.getRangeInt(0, 1));
            pm.setDeviceInfo("DeviceInfo=>  ");
            pm.setReceiver("receiver=>  " + UUID.get());
            pm.setTemplateId((long) RandomUtil.getRangeInt(1, 2000));
            pm.setType(RandomUtil.getRangeInt(1, 4));
            messages.add(pm);
        }
        notificationService.saveAll(messages);

        System.out.println("push message generated");
        return AjaxResponse.success(messages);
    }

    /** generate polling
     * @return
     */
    @RequestMapping(value = "/polling.html", method = RequestMethod.GET)
    public String polling() {
        SleepUtil.sleep(1);
        String ace = "Ace Application => " + UUID.get(10);
        return ace;
    }
}

