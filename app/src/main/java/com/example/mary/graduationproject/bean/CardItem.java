package com.example.mary.graduationproject.bean;


public class CardItem {

    private int mbackResource;
    private String mCityResource;

    public CardItem(String title, int text) {
        mCityResource = title;
        mbackResource = text;
    }

    public int getText() {
        return mbackResource;
    }

    public String getTitle() {
        return mCityResource;
    }
}
