package com.restController;

import com.api.AceApi;
import com.api.Response;
import com.models.common.AjaxResponse;
import com.models.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname: ApiRestController
 * @Date: 9/5/2021 10:04 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/api")
//@Api(tags = "api")
@Tag(name = "Api")
public class ApiRestController {
    private static final Logger log = LogManager.getLogger(ApiRestController.class.getName());

    private final AceApi aceApi;

    @Autowired
    public ApiRestController(AceApi aceApi) {

        this.aceApi = aceApi;
    }

    @GetMapping("/getAllUsers")
    public Response getAllUsers() {
        AjaxResponse ajaxResponse = aceApi.getAllUsers();
        log.info(ajaxResponse.getData());
        return Response.success(ajaxResponse);
    }


}

