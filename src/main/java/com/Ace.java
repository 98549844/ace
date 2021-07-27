package com;

import com.util.PathUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Console;
import util.FileUtil;
import util.SystemUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) {

        double pi = Math.PI;

        double cc = (15.0 / 2) * (8.0 / 2);
        double lc = (10.0 / 2) * (5.0 / 2);

        double d1 = 2 * Math.sqrt(cc);
        double d2 = 2 * Math.sqrt(lc);

        System.out.println(d1);
        System.out.println(d2);
    }


}
