package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;


    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    //一旦调用服务失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的FallbackMethod调用类中的指定方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable Long id) {
        Dept dept=this.service.get(id);
        if(null==dept){
            throw new RuntimeException("该ID："+id+"没用对应的信息");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        return new Dept().setDeptno(id).setDname("该ID"+id+"没有对应的信息，null==@HystrixCommand")
                .setDb_source("no this datasource in Mysql");
    }

}
