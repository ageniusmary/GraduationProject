package com.example.mary.graduationproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.event.MessageEvent;
import com.example.mary.graduationproject.utils.CustomDatePicker;
import com.example.mary.graduationproject.utils.DateFormatUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class PersonalinformationActivity extends AppCompatActivity implements View.OnClickListener{

  private ImageView btn_back;
  private ImageView imageView,imageView1,imageView2,imageView3,imageView4;
  private TextView textView,textView1,textView2,textView3,textView4,textView5;
  private CustomDatePicker mDatePicker;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_personalinformation);
    findView();
    EventBus.getDefault().register(this);
  }

  private void findView() {
    btn_back = findViewById(R.id.btn_back);
    btn_back.setOnClickListener(this);
    imageView = findViewById(R.id.edit_user_image);
    imageView.setOnClickListener(this);
    imageView1 = findViewById(R.id.iv_select_sex);
    imageView1.setOnClickListener(this);
    imageView2 = findViewById(R.id.iv_select_school);
    imageView2.setOnClickListener(this);
    imageView3 = findViewById(R.id.iv_select_work);
    imageView3.setOnClickListener(this);
    imageView4 = findViewById(R.id.iv_select_language);
    imageView4.setOnClickListener(this);

    textView = findViewById(R.id.show_user_name);
    textView1 = findViewById(R.id.edit_user_name);
    textView1.setOnClickListener(this);
    textView2 = findViewById(R.id.tv_edit_broth);
    textView2.setOnClickListener(this);
    textView3 = findViewById(R.id.tv_edit_email);
    textView3.setOnClickListener(this);
    textView4 = findViewById(R.id.tv_edit_tel);
    textView4.setOnClickListener(this);
    textView5 = findViewById(R.id.tv_edit_location);
    textView5.setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.btn_back:
        finish();
        break;
      case R.id.edit_user_image:
        //调用相册

        break;
      case R.id.iv_select_sex:
        //选择性别

        break;
      case R.id.iv_select_school:
        //输入学校

        break;
      case R.id.iv_select_work:
        //输入工作

        break;
      case R.id.iv_select_language:
        //选择语言

        break;
      case R.id.edit_user_name:
        //编辑姓名
        Intent in = new Intent(this,editNameActivity.class);
        startActivity(in);
        break;
      case R.id.tv_edit_broth:
        //选择出生日期
        initDatePicker();
        mDatePicker.show(textView2.getText().toString());
        break;
      case R.id.tv_edit_email:
        //输入邮箱

        break;
      case R.id.tv_edit_tel:
        //输入电话

        break;
      case R.id.tv_edit_location:
        //输入位置

        break;
    }
  }

  private void initDatePicker() {
    long beginTimestamp = DateFormatUtils.str2Long("1995-01-01", false);
    long endTimestamp = System.currentTimeMillis();

    textView2.setText(DateFormatUtils.long2Str(endTimestamp, false));
    //textView2.setText("1998-11-20");

    // 通过时间戳初始化日期，毫秒级别
    mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
      @Override
      public void onTimeSelected(long timestamp) {
        textView2.setText(DateFormatUtils.long2Str(timestamp, false));
      }
    }, beginTimestamp, endTimestamp);
    // 不允许点击屏幕或物理返回键关闭
    mDatePicker.setCancelable(false);
    // 不显示时和分
    mDatePicker.setCanShowPreciseTime(false);
    // 不允许循环滚动
    mDatePicker.setScrollLoop(false);
    // 不允许滚动动画
    mDatePicker.setCanShowAnim(false);
  }

  //修改姓名
  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessageEvent(MessageEvent event){
    textView.setText(event.getMessage());
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }
}
