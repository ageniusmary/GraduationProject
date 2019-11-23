package com.example.mary.graduationproject.bean;

import android.widget.ImageView;

/**
 * 创建时间：2019/2/22
 * 作者： Mary
 * 描述：心愿单页面recycleView的item的bean
 */
public class wishlistItemBean {
  public int mImageView;
  public String mCity;

  public wishlistItemBean() {
  }

  public wishlistItemBean(int mImageView, String mCity) {
    this.mImageView = mImageView;
    this.mCity = mCity;
  }
}
