package com.letterball.utils;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
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

    /**
     * asList()方法的使用
     */
    @Test
    public void arraySort(){
        Integer[] integers = new Integer[] {12,11,9,29};
        for (int i = 0; i < integers.length; i++) {
            System.out.println("数组[?]取值: " + integers[i]);
        }
        List<Integer> stringList = Arrays.asList(integers);
        System.err.println("数组转换List: " + stringList);
        System.out.println("list  get(?)取值: " + stringList.get(0));
        Collections.sort(stringList);
        System.out.println("list排序后: " + stringList);
    }

    /**
     * forEarch
     */
    @Test
    public void forEaechTest(){
        int i = 1;
        while (i < 1000){
            System.out.println(i);
            i++;
        }
    }
}
