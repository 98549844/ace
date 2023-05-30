package com.ace.api;

import org.springframework.web.service.annotation.GetExchange;

/**
 * @Classname: EthGasStation
 * @Date: 2023/5/30 下午 12:40
 * @Author: kalam_au
 * @Description:
 */
public interface EthGasStation {

    @GetExchange("/api/fee-estimate")
    String getFeeEstimate();
}
