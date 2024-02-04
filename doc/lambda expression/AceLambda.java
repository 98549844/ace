package com.ace.lambda;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @Classname: AceLambda
 * @Date: 4/2/2024 8:01 pm
 * @Author: garlam
 * @Description:
 */


public class AceLambda implements AceInterface {
    private static final Logger log = LogManager.getLogger(AceLambda.class.getName());

    public static void main(String[] args) {
        String s = "Hello world !!!";
        //统传写法
        AceInterface ace = new AceLambda();
        ace.getA(s);
        //----------------------------------------------

        //lambda写法
        AceInterface ace1 = s1 -> {
            System.out.println("实现lambda写法: " + s1);
            return s1;
        };

        AceInterface ace2 = s2 -> "lambda精简写法" + s2;

        System.out.println(ace1.getA(s));
        System.out.println(ace2.getA(s));
    }


    @Override
    public String getA(String s) {
        //统传写法
        System.out.println("统传写法: " + s);
        return s;
    }

    public String getB(String s) {
        //统传写法
        System.out.println("统传写法: " + s);
        return s;
    }

    public String getC(String s) {
        //统传写法
        System.out.println("统传写法: " + s);
        return s;
    }
}

