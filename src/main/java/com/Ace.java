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
import util.DateTimeUtil;
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

        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusYears(1);

        Long days = DateTimeUtil.differenceDaysByLocalDateTime(start, end);
        Long days1 = DateTimeUtil.differenceDaysByLocalDateTime(end, start);

        System.out.println(days);
        System.out.println(days1);

    }
}
