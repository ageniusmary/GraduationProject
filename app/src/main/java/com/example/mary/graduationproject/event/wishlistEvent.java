package com.example.mary.graduationproject.event;

import com.example.mary.graduationproject.bean.houseItemBean;

/**
 * 项目名:    GraduationProject
 * 包名：     com.example.mary.graduationproject.event
 * 创建者：   Mary
 * 创建时间:  2019/5/13 15:06
 * 描述：     TODO
 */
public class wishlistEvent {

    houseItemBean data;

    public wishlistEvent(houseItemBean data) {
        this.data = data;
    }

    public houseItemBean getData() {
        return data;
    }

    public void setData(houseItemBean data) {
        this.data = data;
    }
}
