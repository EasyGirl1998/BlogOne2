package com.yc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication//启动类的注解
@EnableEurekaServer//启用Eureka服务器
public class EurekaApp1 {

    public static void main(String[] args) {

        SpringApplication.run(EurekaApp1.class,args);
    }

}