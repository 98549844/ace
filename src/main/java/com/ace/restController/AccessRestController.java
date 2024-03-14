package com.ace.restController;

import com.ace.mapper.AccessLogMapper;
import com.ace.models.entity.AccessLog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: AccessRestController
 * @Date: 29/6/2021 12:55 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/rest/accessLog")
@Tag(name = "Access")
public class AccessRestController {
    private static final Logger log = LogManager.getLogger(AccessRestController.class.getName());

    private final AccessLogMapper accessLogMapper;

    @Autowired
    public AccessRestController(AccessLogMapper accessLogMapper) {
        this.accessLogMapper = accessLogMapper;
    }


    @Operation(summary = "get access log")
    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public boolean getAll() {
        List<AccessLog> ls = accessLogMapper.selectAll();
        log.info("ACCESS LOG TIMES: {}", ls.size());
        //just get first 10
        for (int i = 0; i < 10; i++) {
            ls.get(i).setAccessTime(LocalDateTime.now());
            accessLogMapper.updateByPrimaryKey(ls.get(i));
            System.out.println(ls.get(i).getLogId()+"; ");
        }

        return true;
    }


}

