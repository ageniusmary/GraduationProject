package com.example.mary.graduationproject.event;

/**
 * 项目名:    GraduationProject
 * 包名：     com.example.mary.graduationproject.event
 * 创建者：   Mary
 * 创建时间:  2019/5/12 10:19
 * 描述：     TODO
 */
public class MessageEvent {
    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
