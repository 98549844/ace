package com.ace.api;

import org.springframework.web.service.annotation.GetExchange;

/**
 * @Classname: Blockchain
 * @Date: 8/10/2023 4:16 pm
 * @Author: garlam
 * @Description:
 */
public interface Blockchain {

    //https://api.tatum.io/v3/blockchain/fee/ETH
    @GetExchange("/ETH")
    String getETH();
}
