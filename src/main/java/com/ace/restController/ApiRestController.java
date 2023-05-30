package com.ace.restController;

import com.ace.api.AceApi;
import com.ace.api.Daatm;
import com.ace.api.EthGasStation;
import com.ace.api.Response;
import com.ace.models.common.AjaxResponse;
import com.util.FastJsonUtil;
import com.util.GsonUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
    private final Daatm daatm;
    private final EthGasStation ethGasStation;

    @Autowired
    public ApiRestController(AceApi aceApi, Daatm daatm, EthGasStation ethGasStation) {

        this.aceApi = aceApi;
        this.daatm = daatm;
        this.ethGasStation = ethGasStation;
    }

    @GetMapping("/getAllUsers")
    public Response getAllUsers() {
        AjaxResponse ajaxResponse = aceApi.getAllUsers();
        log.info(ajaxResponse.getData());
        return Response.success(ajaxResponse);
    }

    @GetMapping("/getDaatm")
    public Response getDaatm() {
        String ajaxResponse = daatm.getDaatm();
        log.info(ajaxResponse);
        return Response.success(ajaxResponse);
    }

    @GetMapping("/getFeeEstimate")
    public Response getFeeEstimate() {
        String ajaxResponse = ethGasStation.getFeeEstimate();
        System.out.println(GsonUtil.getPrettyJson(ajaxResponse));
        return Response.success(ajaxResponse);
    }


}

