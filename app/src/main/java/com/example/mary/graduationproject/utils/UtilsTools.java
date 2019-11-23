package com.example.mary.graduationproject.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

/**
 * 创建时间：2019/2/17
 * 作者： Mary
 * 描述：工具统一类
 */

public class UtilsTools {
  //设置字体
  public static void setFont(Context mContext, TextView textview){
    Typeface fontType = Typeface.createFromAsset(mContext.getAssets(),"fonts/FONT.ttf");
    textview.setTypeface(fontType);
  }

}
