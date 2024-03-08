package com.ace;

import com.ace.util.CangJieUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.util.ListUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws Exception {
        String stringList = "[\"2\",\"ADMIN\",\"DISABLE\"]";
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> listData = objectMapper.readValue(stringList, new TypeReference<List<String>>() {
        });
        String a = listData.get(0);
        System.out.println(listData.get(0));
        System.out.println(listData.get(1));
        System.out.println(listData.get(2));

        ListUtil.removeElement(listData, a);
        for (String aa : listData) {
            System.out.println(aa);
        }

    }

    private static void cangJieCheck(String code) throws IOException {
        CangJieUtil.checkCangJieCode(code);
    }

}

