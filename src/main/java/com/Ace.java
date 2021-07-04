package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Console;
import util.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ace {
    private static Logger log = LogManager.getLogger(Ace.class.getName());

    public static void main(String[] args) throws Exception {

        int[][] a = new int[10][5];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                a[i][j] = i + j;
                System.out.print(a[i][j]);
            }
            System.out.println();
        }
    }
}
