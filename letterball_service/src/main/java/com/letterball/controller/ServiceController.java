package com.letterball.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 支付服务
 */
@RestController
@RequestMapping("/payService")
public class ServiceController {


    /**
     * 付款接口
     */
    @RequestMapping("/payToOther")
    public String payToOther(){

        return ("你的账户1326900**** 被扣除200.00");

    }

    /**
     * 收款接口
     */
    @RequestMapping("/payMySelf")
    public String payMySelf(){

        return ("你的账户1326900**** 收款100.00");
    }

}
