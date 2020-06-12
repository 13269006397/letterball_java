package com.letterball.utils;

import java.util.Random;

public class NumberUtils {


    /**
     * 生成6位验证码
     * @return
     */
    public String randomCode(){
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }
}
