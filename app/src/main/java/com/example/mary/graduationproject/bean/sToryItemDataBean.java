package com.example.mary.graduationproject.bean;

/**
 * 项目名:    GraduationProject
 * 包名：     com.example.mary.graduationproject.bean
 * 创建者：   Mary
 * 创建时间:  2019/4/15 16:48
 * 描述：     TODO
 */
public class sToryItemDataBean {
    //标题
    private String title;
    //出处
    private String description;
    //图片的url
    private String imgUrl;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
