package com.ace.restController;

import com.ace.models.common.AjaxResponse;
import com.ace.utilities.UUID;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname: UuidRestController
 * @Date: 30/11/2023 8:08 am
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/uuid")
@Tag(name = "Uuid")
public class UuidRestController {
    private static final Logger log = LogManager.getLogger(UuidRestController.class.getName());


    @RequestMapping(method = RequestMethod.GET, value = "/get.html")
    public AjaxResponse getUuid() {
        return AjaxResponse.success(UUID.get());
    }
}

