package com.service;

import com.api.Response;
import com.service.impl.FeignServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@FeignClient(name = "api-service", fallback = FeignServiceImpl.class)
public interface FeignService {

    /**
     * feign get 请求<单个参数请求>
     *
     * @param params
     * @return
     */
    @GetMapping("/api/feign/getRequest1")
    Response feignGetRequest1(@RequestParam("params") String params);

    /**
     * feign get 请求<多参数请求>
     *
     * @param params1
     * @param params2
     * @return
     */
    @GetMapping("/api/feign/getRequest")
    Response feignGetRequest(@RequestParam("params1") String params1, @RequestParam("params2") String params2);

    /**
     * feign get 请求<单个参数请求>
     *
     * @return
     * @author songfayuan
     * @date 2019/03/25 11:19
     */
    @GetMapping("/api/feign/getRequest3/{params}")
    Response feignGetRequest3(@PathVariable("params") String params);

    /**
     * feign post 请求<单参、多参数请求>
     *
     * @return
     */
    @PostMapping("/api/feign/postRequest")
    Response feignPostRequest(@RequestBody Map<String, Object> params);

    /**
     * feign post 请求<单参数请求>
     *
     * @param params
     * @return
     */
    @PostMapping("/api/feign/postRequest2")
    Response feignPostRequest2(@RequestParam("params") String params);
}
