package com.api;

import com.models.entity.dao.Users;
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
    ApiResponse<Users> create(@RequestBody Users users);

    @PostMapping("/api/delete/{id}")
    ApiResponse<Users> delete(@PathVariable("id") Integer id);

    @PostMapping("/api/getAll")
    ApiResponse<Integer> query(@RequestBody Users users);
}
