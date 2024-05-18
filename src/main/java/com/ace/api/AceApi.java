package com.ace.api;

import com.ace.models.common.RespResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PostExchange;

/**
 * @Classname: AceApi
 * @Date: 9/5/2021 9:52 上午
 * @Author: garlam
 * @Description:
 */



public interface AceApi {

    @GetExchange("/rest/users/getUsers")
    RespResult getAllUsers();

    @PostExchange("/rest/users/get/{userId}")
    RespResult getUserById(@PathVariable Long userId);

}
