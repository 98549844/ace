package com.ace.restController;

import com.ace.api.Response;
import com.ace.models.common.AjaxResponse;
import com.ace.util.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.ace.controller.common.CommonController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Classname: IpAddressRestController
 * @Date: 2/3/2024 11:51 pm
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/ip")
@Tag(name = "Ip Address")
public class IpAddressRestController extends CommonController {
    private static final Logger log = LogManager.getLogger(IpAddressRestController.class.getName());

    private final IpUtil ipUtil;

    public IpAddressRestController(IpUtil ipUtil) {
        this.ipUtil = ipUtil;
    }

    @GetMapping("/getLocation/{ip}")
    @Operation(summary = "Get location by Ip")
    public Response getIpLocationInfo(@PathVariable String ip) {
        String ipLocal = ipUtil.getRegion(ip);
        return Response.success(ipLocal);
    }

}

