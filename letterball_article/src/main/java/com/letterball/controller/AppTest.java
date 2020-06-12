package com.letterball.controller;

import com.letterball.ArticleApplication;
import com.letterball.utils.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticleApplication.class)
public class AppTest {

    @Resource
    RedisUtils redisUtils;

    @Test
    public void setKey(){

        redisUtils.setKeyH("2","大汽车",1);


        String keyDate = redisUtils.getKey("2");

        System.err.println(keyDate);
    }


}
