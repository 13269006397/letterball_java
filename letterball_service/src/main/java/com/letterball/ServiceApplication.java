package com.letterball;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.letterball.mapper")
@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        System.out.println("这是 ServiceApplication服务");
        SpringApplication.run(ServiceApplication.class);
    }
}
