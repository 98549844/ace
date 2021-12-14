package com;

import com.service.UserRolesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.SessionFactory;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.persister.entity.AbstractEntityPersister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import util.entity.Users;

import javax.persistence.EntityManagerFactory;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println("计算两个时间的差：");
        LocalDateTime end = LocalDateTime.now().plusYears(-10);
        Duration duration = Duration.between( end, now);
        long days = duration.toDays(); //相差的天数
        long hours = duration.toHours();//相差的小时数
        long minutes = duration.toMinutes();//相差的分钟数
        long millis = duration.toMillis();//相差毫秒数
        long nanos = duration.toNanos();//相差的纳秒数
        System.out.println(now);
        System.out.println(end);

        System.out.println(days / 365);
        System.out.println("发送短信耗时【 " + days + "天：" + hours + " 小时：" + minutes + " 分钟：" + millis + " 毫秒：" + nanos + " 纳秒】");
    }
}
