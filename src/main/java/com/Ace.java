package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());
   // private final static Log log = LogFactory.getLog(Ace.class);

    public static void main(String[] args) {
        log.traceEntry();
        log.trace("我是trace");
        log.info("我是info信息:{}","ACE_UTIL");
        log.error("我是error");
        log.fatal("我是fatal");

        log.trace("退出程序.");
        log.traceExit();
    }
}
