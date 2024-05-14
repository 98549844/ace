package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.RRWebEvents;
import com.ace.models.entity.Users;
import com.ace.service.RRWebService;
import com.ace.service.UsersService;
import com.ace.utilities.NullUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    private final RRWebService rrWebService;
    private final UsersService usersService;

    public RRWebRestController(RRWebService rrWebService, UsersService usersService) {
        this.rrWebService = rrWebService;
        this.usersService = usersService;
    }


    @Operation(summary = "保存记录")
    @RequestMapping(method = RequestMethod.POST, value = "/save.html")
    public AjaxResponse save(@ModelAttribute RRWebEvents rrWebEvents) {
        if (NullUtil.isNull(rrWebEvents) || NullUtil.isNull(rrWebEvents.getEventData())) {
            log.info("EventData is empty !");
            return AjaxResponse.success(true);
        }
        log.info("access /rrweb/save: {} ", rrWebEvents.getUuid());

        Users user = getCurrentUser();
        //从session拿取用户资料set到rrWebEvents对像里
        rrWebEvents.setUserAccount(user.getUserAccount());
        rrWebEvents.setUserId(user.getUserId());
        rrWebEvents.setUserName(user.getUsername());
        String eventData = rrWebEvents.getEventData().trim();
        rrWebEvents.setEventData(eventData);
        rrWebService.save(rrWebEvents);
        return AjaxResponse.success(true);
    }


    @Operation(summary = "回放")
    @RequestMapping(method = RequestMethod.GET, value = "/playback/{userAccount}/{uuid}")
    public AjaxResponse playback(@PathVariable String userAccount, @PathVariable String uuid) throws IOException {
        log.info("RRWeb userAccount: {} | uuid: {}", userAccount, uuid);

        List<RRWebEvents> events = rrWebService.findByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);

        StringBuilder data = new StringBuilder();
        for (RRWebEvents ev : events) {
            String eventData = ev.getEventData();
            //eventData 不大过2,表示只有中括号, 而且不含数据, 跳过拼接数据
            if (eventData.length() > 2) {
                //拆中括号"[" "]", 拼入逗号 ","
                String sub = eventData.substring(1, eventData.length() - 1) + ',';
                data.append(sub);
            }
        }
        //拼接中括号"[ ]"
        String result = "[" + data.substring(0, data.length() - 1) + "]";
        return AjaxResponse.success(result);
    }

    @Operation(summary = "回放列表")
    @RequestMapping(method = RequestMethod.GET, value = "/getPlaybackList.html")
    public AjaxResponse getPlaybackList() {
        return AjaxResponse.success(true);
    }

    @Operation(summary = "更新isRecord")
    @RequestMapping(method = RequestMethod.GET, value = "/updateIsRecord.html")
    public AjaxResponse updateIsRecord(@RequestParam(value = "userId") Long userId) {
        Users users = usersService.findUsersById(userId);
        users.setRecord(!users.isRecord());
        users = usersService.saveAndFlush(users);
        System.out.println("users update: " + users.isRecord());
        return AjaxResponse.success(users.isRecord());
    }


    @Operation(summary = "删除回放影片")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    public AjaxResponse delete(@PathVariable String uuid) {
        log.info("access /rrweb/delete: {} ", uuid);
        boolean result = false;
        if (getCurrentUser().getRoleGroup().contains(Users.ADMIN)) {
            result = rrWebService.deleteByUuid(uuid);
        }
        return AjaxResponse.success(result);
    }

}


