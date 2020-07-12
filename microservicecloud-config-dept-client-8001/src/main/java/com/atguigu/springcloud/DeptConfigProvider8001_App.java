package com.atguigu.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient  //本服务启动后自动注册进eureka中
public class DeptConfigProvider8001_App {

    public static void main(String[] args) {
        SpringApplication.run(DeptConfigProvider8001_App.class, args);
    }
}
