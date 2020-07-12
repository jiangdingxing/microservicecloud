package com.atguigu.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
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

   /* //将默认的轮询策略修改为随机策略
    @Bean
    public IRule myRule() {
        //return    new WeightedResponeseTimeRule (); //Ribbon的权重策略
        return new RetryRule(); //Ribbon的随机策略
    } 已使用自定义策略无需在调用ribbon提供的策略*/

}
// 复习一下spring 和 springboot
//    @Bean
//    public UserService getUserService(){
//        return new UserServiceImpl();
//    }
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userService" class="com.atguigu.springcloud.service.UserServiceImpl"/>