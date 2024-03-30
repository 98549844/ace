package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.PushMessage;
import com.ace.service.NotificationService;
import com.ace.utilities.RandomUtil;
import com.ace.utilities.SleepUtil;
import com.ace.utilities.UUID;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

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

    /**
     * generate polling
     *
     * @return
     */
    @Async("asyncTaskExecutor")
    @RequestMapping(value = "/polling.html", method = RequestMethod.GET)
    public CompletableFuture<String> polling() {
        ++count;
        SleepUtil.sleep(RandomUtil.getRangeInt(1, 8));
        String ace = "Ace Application => " + " (thread id =>" + getThreadId() + ")  随机数=>" + UUID.get(10);
      //  String ace = "Ace Application => " + "  随机数=>" + UUID.get(10);
        return CompletableFuture.completedFuture(ace);
    }

    private String getThreadId(){
        // 设置线程ID到ThreadLocal
        threadId.set(Thread.currentThread().getId());
        // 执行异步操作
        // 获取线程ID
        Long currentThreadId = threadId.get();
        // 清除ThreadLocal中的线程ID
        threadId.remove();
        // 返回异步结果和线程ID
        return currentThreadId.toString();
    }
    private static int count;
    private ThreadLocal<Long> threadId = new ThreadLocal<>();
}

