package com.example.mary.graduationproject.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mary.graduationproject.Activity.LoginActivity;
import com.example.mary.graduationproject.MainActivity;
import com.example.mary.graduationproject.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/2/17
 * 作者： Mary
 * 描述： 闪屏页ui
 */

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {

  private ViewPager mViewPager;
  //容器
  private List<View> mList = new ArrayList<>();
  private View view1, view2, view3;
  //小圆点
  private ImageView point1, point2, point3;
  //跳过
  private ImageView iv_back;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_guide);
    Log.i("GuideActivity","onCreate");
    initView();
  }

  //初始化View
  private void initView() {
    iv_back = findViewById(R.id.iv_back);
    iv_back.setOnClickListener(this);

    point1 = findViewById(R.id.point1);
    point2 = findViewById(R.id.point2);
    point3 = findViewById(R.id.point3);

    //设置默认图片
    setPoint(true, false, false);

    mViewPager = findViewById(R.id.mViewPager);

    view1 = View.inflate(this, R.layout.pager_item_one, null);
    view2 = View.inflate(this, R.layout.pager_item_two, null);
    view3 = View.inflate(this, R.layout.pager_item_three, null);

    view3.findViewById(R.id.btn_start).setOnClickListener(this);

    mList.add(view1);
    mList.add(view2);
    mList.add(view3);

    mViewPager.setAdapter(new GuideAdapter());

    //设置ViewPager滑动监听
    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

      }

      //切换
      @Override
      public void onPageSelected(int position) {
        switch (position) {
          case 0:
            setPoint(true, false, false);
            iv_back.setVisibility(View.VISIBLE);
            break;
          case 1:
            setPoint(false, true, false);
            iv_back.setVisibility(View.VISIBLE);
            break;
          case 2:
            setPoint(false, false, true);
            iv_back.setVisibility(View.GONE);
            break;
        }
      }

      @Override
      public void onPageScrollStateChanged(int state) {

      }
    });
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.btn_start:
      case R.id.iv_back:
        startActivity(new Intent(this, LoginActivity.class));
        finish();
        break;
    }
  }

  class GuideAdapter extends PagerAdapter {

    @Override
    public int getCount() {
      return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      ((ViewPager)container).addView(mList.get(position));
      return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      ((ViewPager)container).removeView(mList.get(position));
    }
  }

  //设置小圆点的选中效果
  private void setPoint(boolean b, boolean b1, boolean b2) {
    if (b) {
      point1.setBackgroundResource(R.mipmap.circle);
    } else {
      point1.setBackgroundResource(R.mipmap.circle1);
    }
    if (b1) {
      point2.setBackgroundResource(R.mipmap.circle);
    } else {
      point2.setBackgroundResource(R.mipmap.circle1);
    }
    if (b2) {
      point3.setBackgroundResource(R.mipmap.circle);
    } else {
      point3.setBackgroundResource(R.mipmap.circle1);
    }
  }
}
