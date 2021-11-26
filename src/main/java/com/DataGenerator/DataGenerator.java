package com.DataGenerator;


import com.models.entity.dao.Users;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerator {

    public static void main(String[] args) {
        List<Users> ls = getTestEntity();
        for (Users t : ls) {
            System.out.println(t.toString());
        }
    }

    public static List<Users> getTestDataList() {
        Users u1 = new Users();
        u1.setUsername("Garlam");
        u1.setEmail("garlam_au@qq.com");

        Users u2 = new Users();
        u2.setUsername("lily");
        u2.setEmail("lily_fu@qq.com");

        Users u3 = new Users();
        u3.setUsername("peter");
        u3.setEmail("peter_lee@qq.com");

        Users u4 = new Users();
        u4.setUsername("mary");
        u4.setEmail("mary_ma@qq.com");

        List<Users> ulist = new ArrayList<>();
        ulist.add(u1);
        ulist.add(u2);
        ulist.add(u3);
        ulist.add(u4);
        return ulist;
    }

    /**
     * @return
     */
    public static List<Users> getTestEntity() {
        List<Users> testList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            if (i < 30) {
                Users test = new Users();
                test.setUserId(new Long(get4Int()));
                test.setUserId(new Long(get1Int()));
                test.setUsername("Peter");
                test.setEmail(get2Int() + "peter@domain.com");
                test.setCreatedBy(1004l);
                test.setLastUpdatedBy(1004l);
                test.setCreatedDate(LocalDateTime.now());
                test.setLastUpdateDate(LocalDateTime.now());
                testList.add(test);
            } else if (i >= 30 && i < 85) {
                Users test = new Users();
                test.setUserId(new Long(get4Int()));
                test.setUserId(new Long(get1Int()));
                test.setUsername("Lily");
                test.setEmail(get2Int() + "lily@domain.com");
                test.setCreatedBy(1004l);
                test.setLastUpdatedBy(1004l);
                test.setCreatedDate(LocalDateTime.now());
                test.setLastUpdateDate(LocalDateTime.now());
                testList.add(test);
            } else {
                Users test = new Users();
                test.setUserId(new Long(get4Int()));
                test.setUserId(new Long(get1Int()));
                test.setUsername("Garlam");
                test.setEmail(get2Int() + "garlam@domain.com");
                test.setCreatedBy(1001l);
                test.setLastUpdatedBy(1001l);
                test.setCreatedDate(LocalDateTime.now());
                test.setLastUpdateDate(LocalDateTime.now());
                testList.add(test);
            }
        }
        return testList;
    }



    private static int get1Int() {
        Random random = new SecureRandom();
        int i = random.nextInt(9) + 1;
        return i;
    }

    private static int get2Int() {
        Random random = new SecureRandom();
        int i = random.nextInt(90) + 10;
        return i;
    }

    private static int get3Int() {
        SecureRandom random = new SecureRandom();
        int i = random.nextInt(900) + 100;
        return i;
    }

    private static int get4Int() {
        Random random = new SecureRandom();
        int i = random.nextInt(9000) + 1000;
        return i;
    }

    private static int get5Int() {
        Random random = new SecureRandom();
        int i = random.nextInt(90000) + 10000;
        return i;
    }

    private static int get6Int() {
        Random random = new SecureRandom();
        int i = random.nextInt(900000) + 100000;
        return i;
    }


}
