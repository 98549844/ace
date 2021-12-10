package com;

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
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) {

        System.out.println(LocalDateTime.now().plusYears(1));
    }
}
