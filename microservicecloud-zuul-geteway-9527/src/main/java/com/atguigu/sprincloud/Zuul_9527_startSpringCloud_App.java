package com.atguigu.sprincloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class Zuul_9527_startSpringCloud_App {
    public static void main(String[] args) {
        System.out.println("qwe");
        SpringApplication.run(Zuul_9527_startSpringCloud_App.class,args);
    }
}
