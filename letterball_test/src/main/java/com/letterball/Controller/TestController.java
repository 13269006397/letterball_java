package com.letterball.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这是测试用户机
 */

@RequestMapping("/testService")
@RestController
public class TestController {

    /**
     * 调用支付
     */
    @RequestMapping("/test1")
    public String test1(){


        return null;
    }



}
