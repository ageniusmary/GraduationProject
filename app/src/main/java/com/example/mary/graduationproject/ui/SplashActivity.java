package com.example.mary.graduationproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import com.example.mary.graduationproject.MainActivity;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.utils.ShareUtils;
import com.example.mary.graduationproject.utils.StaticClass;
import com.example.mary.graduationproject.utils.UtilsTools;

/**
 * 创建时间：2019/2/17
 * 作者： Mary
 * 描述： 1. 闪屏页
 * 2. 判断程序是否第一次运行
 * 3. 延时2000ms
 * 4. Activity全屏幕主题
 */

public class SplashActivity extends AppCompatActivity {

  private TextView tv_splash;

  private Handler handler = new Handler() {
    @Override
    public void handleMessage(Message msg) {
      super.handleMessage(msg);
      switch (msg.what) {
        case StaticClass.HANDLER_SPLASH:
          if (isFirst()) {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
          } else {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
          }
          finish();
          break;
      }
    }
  };

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash);
    Log.d("splash","onCreate");
    initView();
  }

  //初始化view
  private void initView() {
    //延时2000ms
    handler.sendEmptyMessageDelayed(StaticClass.HANDLER_SPLASH, 2000);
    tv_splash = findViewById(R.id.tv_splash);
    Log.d("splash","毕业设计");
    tv_splash.setText("Mary的毕业设计");
    //设置字体
    //UtilsTools.setFont(this, tv_splash);
  }

  //判断程序是否第一次运行
  private boolean isFirst() {
    boolean isFirst = ShareUtils.getBoolean(this, StaticClass.SHARE_IS_First, true);
    if (isFirst) {
      //标记我们已经启动的app，第一次运行后设置为false，以后在打开就不用打开闪屏页
      ShareUtils.putBoolean(this, StaticClass.SHARE_IS_First, false);
      return true;
    } else {
      return false;
    }
  }

  //禁止返回键

  @Override
  public void onBackPressed() {
  }

}
