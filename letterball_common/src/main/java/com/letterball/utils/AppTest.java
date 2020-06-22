package com.letterball.utils;

import org.junit.Test;


/**
 * 测试类
 */
public class AppTest {


    @Test
    public void getTestCode(){
        String s = new NumberUtils().randomUUID();
        System.out.println(s);
        System.out.println(s.length());


    }



}
