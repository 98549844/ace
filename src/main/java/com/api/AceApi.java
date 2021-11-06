package com.api;

import com.models.entity.dao.Test;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Classname: AceApi
 * @Date: 9/5/2021 9:52 上午
 * @Author: garlam
 * @Description:
 */


@FeignClient(name = "aceJob", url = "http://localhost:8088/")
public interface AceApi {

    @GetMapping("/api/test/get")
    ApiResponse getTest(@RequestParam("params") String params);

    @PostMapping("/api/insert")
    ApiResponse<Test> create(@RequestBody Test test);

    @PostMapping("/api/delete/{id}")
    ApiResponse<Test> delete(@PathVariable("id") Integer id);

    @PostMapping("/api/getAll")
    ApiResponse<Integer> query(@RequestBody Test test);
}
