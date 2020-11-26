package com.letterball.utils;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;


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

    /**
     * URLEncoder转码   转码为码值
     */
    @Test
    public void urlEncode() throws UnsupportedEncodingException {

        String name=java.net.URLEncoder.encode("雷军.jpg", "UTF-8");
        //  %E9%9B%B7%E5%86%9B.jpg
        System.out.println(name);

    }

    /**
     * URLEncoder解码  解码
     */
    @Test
    public void urlDecode(){
        String name = "%E9%9B%B7%E5%86%9B.jpg";
        //  雷军.jpg
        System.err.println(URLDecoder.decode(name));
    }

    /**
     * 图片压缩
     */
    @Test
    public void jpgTest(){
        File file = new File("D:/myProject/photo/天气之子4k壁纸3840x2160_彼岸图网.jpg");
        String newFilePath = "D:/myProject/photo/abcd123.jpg";

        ImageUtil imageUtil = new ImageUtil();
        try {
            imageUtil.zoomImageScale(file, newFilePath, 500, 300);
        }catch (IOException e){
            System.out.println("压缩错误");
        }
        System.out.println("压缩成功");
    }
}
