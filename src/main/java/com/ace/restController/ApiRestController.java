package com.ace.restController;

import com.ace.api.AceApi;
import com.ace.api.Blockchain;
import com.ace.api.Response;
import com.ace.models.common.AjaxResponse;
import com.util.GsonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    private final Blockchain blockchain;

    public ApiRestController(AceApi aceApi, Blockchain blockchain) {
        this.aceApi = aceApi;
        this.blockchain = blockchain;
    }

    @GetMapping("/getAllUsers")
    public Response getAllUsers() {
        log.info("access api");
        AjaxResponse ajaxResponse = aceApi.getAllUsers();
        log.info(ajaxResponse.getData());
        return Response.success(ajaxResponse);
    }

    @GetMapping("/get/{userId}")
    public Response getById(@PathVariable Long userId) {
        log.info("access api.getUserById: {}", userId);
        AjaxResponse ajaxResponse = aceApi.getUserById(userId);
        log.info(ajaxResponse.getData());
        return Response.success(ajaxResponse);
    }


    @GetMapping("/getFee")
    public Response getFeeEstimate() {
        String ajaxResponse = blockchain.getETH();
        System.out.println(GsonUtil.getPrettyJson(ajaxResponse));
        return Response.success(ajaxResponse);
    }


}

