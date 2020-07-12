package com.aiguigu.springcloud.controller;

import com.aiguigu.springcloud.service.DeptService;
import com.atguigu.springcloud.entities.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService service;
    /*eureka的服务发现提供接口*/
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return service.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    public Dept get(@PathVariable Long id) {
        return service.get(id);
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return service.list();
    }

    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = client.getServices();
        System.out.println("********" + list);

        List<ServiceInstance> sevList = client.getInstances("microservicecloud-dept");
        for (ServiceInstance element : sevList) {
            System.out.println(element.getServiceId() + "\t" + element.getHost() + element.getUri());
        }
        return this.client;
    }
}
