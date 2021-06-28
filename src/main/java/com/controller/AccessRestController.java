package com.controller;

import com.dao.AccessLogDao;
import com.entity.dao.AccessLog;
import com.mapper.AccessLogMapper;
import io.swagger.annotations.Api;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import util.NullUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Classname: AccessRestController
 * @Date: 29/6/2021 12:55 上午
 * @Author: garlam
 * @Description:
 */

@RestController
@RequestMapping("/accesslog")
@Api(tags = "accesslog")
@EnableConfigurationProperties
public class AccessRestController {
    private static Logger log = LogManager.getLogger(AccessRestController.class.getName());

    private AccessLogMapper accessLogMapper;

    @Autowired
    public AccessRestController(AccessLogMapper accessLogMapper) {
        this.accessLogMapper = accessLogMapper;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get")
    public boolean getAll() {
        List<AccessLog> ls = accessLogMapper.selectAll();
        log.info("ACCESS LOG TIMES: {}", ls.size());

        if (NullUtil.isNotNull(ls) && ls.size() >= 2) {
            accessLogMapper.deleteByPrimaryKey(ls.get(0).getLogId());
            ls.get(1).setLastUpdateDate(LocalDateTime.now());
            ls.get(1).setVersion(ls.get(1).getVersion() + 1);

            accessLogMapper.updateByPrimaryKey(ls.get(1));
        }
        return true;
    }


}

