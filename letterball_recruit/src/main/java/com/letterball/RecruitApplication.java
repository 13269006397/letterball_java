package com.letterball;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.letterball.mapper")
@SpringBootApplication
@EnableCaching
public class RecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecruitApplication.class);
    }
}
