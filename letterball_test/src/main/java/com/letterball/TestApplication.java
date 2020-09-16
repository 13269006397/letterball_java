package com.letterball;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.letterball.mapper")
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
        System.out.println("这是 TestApplication服务");
        SpringApplication.run(TestApplication.class);
    }
}
