package com;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DateTimeUtil;

import java.time.LocalDateTime;

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
