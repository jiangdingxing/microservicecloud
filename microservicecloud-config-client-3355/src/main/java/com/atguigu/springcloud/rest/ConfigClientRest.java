package com.atguigu.springcloud.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConfigClientRest {

    // @Value() 注解是用来读取配置文件的信息
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${eureka.client.service-url.defaultZone}")
    private String eurekaServers;

    @Value("${server.port}")
    private String port;

    @RequestMapping("/config")
    public String getConfig(){
        String str="applicationName:"+applicationName+"\t eurekaServers:"+eurekaServers+"\t post:"+port;
        System.out.println("=========="+str);
        return str;

    }
}

