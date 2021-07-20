package com.atguigu.springcloud.elasticsearch;

/**
 * @author jdx
 * @date 2021-07-18 19:50
 * @description: 文档实体
 */
public class UserJdx {
    private String name;
    private Integer age;
    private Integer sex; //1男 2女

    public UserJdx() {
    }

    public UserJdx(String name, Integer age, Integer sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
