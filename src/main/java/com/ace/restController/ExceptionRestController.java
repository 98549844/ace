package com.ace.restController;

import com.ace.controller.common.CommonController;
import com.ace.models.common.AjaxResponse;
import com.ace.models.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname: ExceptionTestController
 * @Date: 13/12/2021 2:22 AM
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/exception")
//@Api(tags = "exception")
@Tag(name = "Exception")
public class ExceptionRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(ExceptionRestController.class.getName());

    @RequestMapping(method = RequestMethod.GET, value = "/nullPointerException")
    public AjaxResponse nullPointerException() {
        Users users = null;
        System.out.println(users.getUserAccount());

        return AjaxResponse.success("SUCCESS");
    }

}

