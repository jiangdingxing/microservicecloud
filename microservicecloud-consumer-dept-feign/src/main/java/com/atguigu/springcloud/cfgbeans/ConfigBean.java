package com.atguigu.springcloud.cfgbeans;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {  //boot -->spring   applicationContext.xml ---@Configuration配置 ConfigBean = applicationContext.xml
    @Bean
    @LoadBalanced //spring cloud Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡的工具
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }



}
// 复习一下spring 和 springboot
//    @Bean
//    public UserService getUserService(){
//        return new UserServiceImpl();
//    }
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userService" class="com.atguigu.springcloud.service.UserServiceImpl"/>