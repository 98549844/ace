package com.ace.scheduler;


import com.ace.constants.AceEnvironment;
import com.ace.models.entity.Users;
import com.ace.restController.UserRolePermissionRestController;
import com.ace.scheduler.task.CleanLog;
import com.ace.scheduler.task.CleanTmp;
import com.ace.service.UsersService;
import com.ace.utilities.NullUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

//@Scheduled注解各参数详解
//https://www.jianshu.com/p/1defb0f22ed1
//      cron表达式语法
//      [秒] [分] [小时] [日] [月] [周] [年]
//      每隔5秒执行一次：*/5 * * * * ?
//		每隔1分钟执行一次：0 */1 * * * ?
//		每天23点执行一次：0 0 23 * * ?
//		每天凌晨1点执行一次：0 0 1 * * ?
//		每月1号凌晨1点执行一次：0 0 1 1 * ?
//		每月最后一天23点执行一次：0 0 23 L * ?
//		每周星期天凌晨1点实行一次：0 0 1 ? * L
//		在26分、29分、33分执行一次：0 26,29,33 * * * ?
//		每天的0点、13点、18点、21点都执行一次：0 0 0,13,18,21 * * ?


@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   //2.开启定时任务
public class Scheduler {

    private final String tmp;
    private final UsersService usersService;
    private final UserRolePermissionRestController userRolePermissionRestController;



    public Scheduler(AceEnvironment aceEnvironment, UsersService usersService, UserRolePermissionRestController userRolePermissionRestController) {
        this.tmp = aceEnvironment.getTmp();
        this.usersService = usersService;
        this.userRolePermissionRestController = userRolePermissionRestController;
    }

    //直接指定时间间隔，例如：5秒 = 5000
    //@Scheduled(fixedRate = 600000) //十分钟执行一次
    @Scheduled(cron = "0 0 */3 * * ?") // 每三小时执行一次
    private void cleanLog() throws IOException {
        CleanLog c = new CleanLog();
        c.clearLog();
    }

    @Scheduled(cron = "0 0 23 * * ?") //每天23点执行一次
    private void cleanTmp() {
        CleanTmp cleanTmp = new CleanTmp();
        cleanTmp.clean(tmp);
    }

    @Scheduled(initialDelay = 0) //立即执行一次
    private void addDefaultAdminUsersIfNotExist() {
        Users admin = usersService.findByUserAccount("admin");
        Users root = usersService.findByUserAccount("root");
        if (NullUtil.isNull(admin) || NullUtil.isNull(root)) {
            userRolePermissionRestController.addDefaultAdminUsers();
        }
    }

}
