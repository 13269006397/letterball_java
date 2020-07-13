package com.letterball.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


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


    @Test
    public void sort(){
        List<String> strings = Arrays.asList("6", "1", "3", "1","2");

        for (String string : strings) {

            System.out.print(string);
        }

        Collections.sort(strings);//sort方法在这里

        for (String string : strings) {

            System.err.print(string);
        }

    }
}
