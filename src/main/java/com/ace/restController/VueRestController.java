package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.models.view.FormData;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname: VueRestController
 * @Date: 2023/8/4 下午 12:12
 * @Author: kalam_au
 * @Description:
 */

@CrossOrigin // 解决浏览器禁止ajax请求本地以外的资源, 后端同时在Controller层的类上增加@CrossOrign注解
@RestController
@RequestMapping("/rest/vue")
@Tag(name = "Ace")
public class VueRestController {
    private static final Logger log = LogManager.getLogger(VueRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public AjaxResponse getAceProperties() {
        List aceList = new ArrayList();
        aceList.add("[ ACE ]");
        aceList.add("version 2.5");
        System.out.println("[ ACE ]" + "   " + "version 2.5");
        return AjaxResponse.success(aceList);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getAce")
    public String getAce() {
        String result = "[ ACE ]" + "   " + "version 2.5";
        System.out.println(result);
        return result;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/formData")
    public AjaxResponse text(@RequestBody FormData formData) {
        String ace = "[ ACE ]"+" ";
        String name = formData.getName() + " ";
        String email = formData.getEmail() + " ";
        String result = ace + name + email;
        System.out.println(result);
        return AjaxResponse.success(result);
    }

}

