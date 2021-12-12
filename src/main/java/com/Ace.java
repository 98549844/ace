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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {

        String str = null;
        try {
            System.out.println(str.charAt(0));
            //System.out.println(1/0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.fillInStackTrace().getMessage());
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            String strs = sw.toString();
            System.out.println("--------------------------美丽的分割线----------------------");
            System.out.println(strs);
        }


    }
}
