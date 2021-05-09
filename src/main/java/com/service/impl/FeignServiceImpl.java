package com.service.impl;

import com.api.Response;
import com.service.FeignService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Classname: FeignServiceImpl
 * @Date: 9/5/2021 8:08 下午
 * @Author: garlam
 * @Description:
 */

@Service
public class FeignServiceImpl implements FeignService {
    private static Logger log = LogManager.getLogger(FeignServiceImpl.class.getName());


    @Override
    public Response feignGetRequest1(String params) {
        log.warn("调用{}异常...", "/demo/noAuthorization/feignGetRequest1");
        return null;
    }

    @Override
    public Response feignGetRequest(String params1, String params2) {
        log.warn("调用{}异常...", "/demo/noAuthorization/feignGetRequest");
        return null;
    }

    @Override
    public Response feignGetRequest3(String params) {
        log.warn("调用{}异常...", "/demo/noAuthorization/feignGetRequest3");
        return null;
    }

    @Override
    public Response feignPostRequest(Map<String, Object> params) {
        log.warn("调用{}异常...", "/demo/noAuthorization/feignPostRequest");
        return null;
    }

    @Override
    public Response feignPostRequest2(String params) {
        log.warn("调用{}异常...", "/demo/noAuthorization/feignPostRequest2");
        return null;
    }
}

