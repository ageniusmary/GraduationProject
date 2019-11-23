package com.example.mary.graduationproject.bean;

/**
 * 项目名:    GraduationProject
 * 包名：     com.example.mary.graduationproject.bean
 * 创建者：   Mary
 * 创建时间:  2019/5/16 20:15
 * 描述：     TODO
 */
public class MyUser {
    private int age;
    private boolean sex;
    private String desc;
    public String Username = "Mary";
    public String Password = "123456";

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
