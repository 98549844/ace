package com.ace;

import com.ace.generator.InsertUsers;
import com.ace.models.entity.Users;
import com.ace.utilities.FastJson2Util;
import com.ace.utilities.GsonUtil;
import com.ace.utils.CangJieUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @Classname: Ace
 * @Date: 2023/10/6 上午 09:19
 * @Author: kalam_au
 * @Description:
 */


public class Ace extends CangJieUtil {
    private static final Logger log = LogManager.getLogger(Ace.class.getName());


    public static void main(String[] args) throws Exception {
      //  checkCangJieCode(null);

        List<Users> user = InsertUsers.insertUsers();

        System.out.println(FastJson2Util.formatJson(FastJson2Util.toJson(user)));
    }


}

