package com.example.mary.graduationproject.bean;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * 创建时间：2019/2/21
 * 作者： Mary
 * 描述：'我的'页面recycleView的item的bean
 */

public class settingItemBean {
  public String mTextView;
  public int mImageView;

  public settingItemBean(String mTextView, int mImageView) {
    this.mTextView = mTextView;
    this.mImageView = mImageView;
  }
}
