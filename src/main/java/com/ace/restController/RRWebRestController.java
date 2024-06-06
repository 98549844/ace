package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.RespResult;
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
    public RespResult save(@ModelAttribute RRWebEvents rrWebEvents) {
        if (NullUtil.isNull(rrWebEvents) || NullUtil.isNull(rrWebEvents.getEventData())) {
            log.info("EventData is empty !");
            return RespResult.success(true);
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
        return RespResult.success(true);
    }


    @Operation(summary = "回放")
    @RequestMapping(method = RequestMethod.GET, value = "/playback/{userAccount}/{uuid}")
    public RespResult playback(@PathVariable String userAccount, @PathVariable String uuid) throws IOException {
        log.info("RRWeb userAccount: {} | uuid: {}", userAccount, uuid);
        List<RRWebEvents> events = rrWebService.findByUserAccountAndUuidOrderByCreatedByAsc(userAccount, uuid);
        String result = rrWebService.appendEventData(events);
        return RespResult.success(result);
    }

    @Operation(summary = "更新isRecord")
    @RequestMapping(method = RequestMethod.GET, value = "/updateIsRecord.html")
    public RespResult updateIsRecord(@RequestParam(value = "userId") Long userId) {
        Users users = usersService.findUsersById(userId);
        users.setRecord(!users.isRecord());
        users = usersService.saveAndFlush(users);
        System.out.println("users update: " + users.isRecord());
        return RespResult.success(users.isRecord());
    }


    @Operation(summary = "删除回放影片")
    @RequestMapping(method = RequestMethod.GET, value = "/delete/{uuid}")
    public RespResult delete(@PathVariable String uuid) {
        log.info("access /rrweb/delete: {} ", uuid);
        boolean result = false;
        if (getCurrentUser().getRoleGroup().contains(Users.ADMIN)) {
            result = rrWebService.deleteByUuid(uuid);
        }
        return RespResult.success(result);
    }

}


