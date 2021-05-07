package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Ace {
    private static Logger logger = LogManager.getLogger(Ace.class.getName());
    private final static Log log = LogFactory.getLog(Ace.class);

    public static void main(String[] args) {
        List<String> a  = new ArrayList<>();
        a.add("B");

        try {

        String c = a.get(2);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
