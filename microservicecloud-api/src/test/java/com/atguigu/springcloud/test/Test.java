package com.atguigu.springcloud.test;


import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.parser.JSONParser;

import java.util.HashMap;
import java.util.Map;

public class Test {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String,Object>();
        Map<String, Object> map1 = new HashMap<String,Object>();
        map1.put("b", "bbb");

        map.put("a1", map1);

        Map<String, Object> test = new HashMap<String,Object>();
        test = ( Map<String, Object>)map.get("a1");
        test.put("b","hahaahah");
        System.out.println(map1.get("b"));
}

}
