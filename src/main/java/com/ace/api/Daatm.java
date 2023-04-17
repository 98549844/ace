package com.ace.api;

import com.ace.models.common.AjaxResponse;
import org.springframework.web.service.annotation.GetExchange;

/**
 * @Classname: Daatm
 * @Date: 2023/4/17 下午 04:50
 * @Author: kalam_au
 * @Description:
 */
public interface Daatm {
    @GetExchange("/api/000/20220210/quote/btc/")
    String getDaatm();
}
