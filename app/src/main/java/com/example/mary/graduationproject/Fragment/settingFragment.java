package com.example.mary.graduationproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.mary.graduationproject.Activity.DetailsPageActivity;
import com.example.mary.graduationproject.Activity.PersonalinformationActivity;
import com.example.mary.graduationproject.Adapter.settingRecyclerViewAdapter;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.View.CircleImageView;
import com.example.mary.graduationproject.bean.settingItemBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/2/18
 * 作者： Mary
 * 描述：'我的'fragment
 */

public class settingFragment extends Fragment implements View.OnClickListener {

  private LinearLayout mLinearLayout;
  private RelativeLayout rl1,rl2,rl3,rl4,rl5,rl6,rl7;
  private TextView tv_user_name;
  private CircleImageView mCircleImageView;
  private ImageView m1,m2,m3,m4,m5,m6,m7;
  private List<settingItemBean> mList = new ArrayList<>();

  public settingFragment(){

  }
  public static settingFragment getInstance(){
    settingFragment settingFragment = new settingFragment();
    return settingFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_setting, null);
    findView(view);
    return view;
  }

  private void findView(View view) {
    mLinearLayout = view.findViewById(R.id.ll_setting_message);
    mLinearLayout.setOnClickListener(this);
    tv_user_name = view.findViewById(R.id.tv_user_name);
    mCircleImageView = view.findViewById(R.id.iv_img_user);

    mCircleImageView = view.findViewById(R.id.iv_img_user);

    rl1 = view.findViewById(R.id.rl_setting_xc);
    rl1.setOnClickListener(this);
    rl2 = view.findViewById(R.id.rl_setting_tz);
    rl2.setOnClickListener(this);
    rl3 = view.findViewById(R.id.rl_setting_yq);
    rl3.setOnClickListener(this);
    rl4 = view.findViewById(R.id.rl_setting_fb);
    rl4.setOnClickListener(this);
    rl5 = view.findViewById(R.id.rl_setting_sz);
    rl5.setOnClickListener(this);
    rl6 = view.findViewById(R.id.rl_setting_hq);
    rl6.setOnClickListener(this);
    rl7 = view.findViewById(R.id.rl_setting_fk);
    rl7.setOnClickListener(this);

    tv_user_name = view.findViewById(R.id.tv_user_name);
    m1 = view.findViewById(R.id.iv_setting_image);
    m1.setImageResource(R.mipmap.setting_xc);
    m2 = view.findViewById(R.id.iv_setting_image1);
    m2.setImageResource(R.mipmap.setting_tz);
    m3 = view.findViewById(R.id.iv_setting_image2);
    m3.setImageResource(R.mipmap.setting_yq);
    m4 = view.findViewById(R.id.iv_setting_image3);
    m4.setImageResource(R.mipmap.setting_fb);
    m5 = view.findViewById(R.id.iv_setting_image4);
    m5.setImageResource(R.mipmap.setting_sz);
    m6 = view.findViewById(R.id.iv_setting_image5);
    m6.setImageResource(R.mipmap.setting_bz);
    m7 = view.findViewById(R.id.iv_setting_image6);
    m7.setImageResource(R.mipmap.setting_fk);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()){
      case R.id.ll_setting_message:
        //跳转个人信息页面
        Toast.makeText(getContext(), "个人信息页面", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getActivity(), PersonalinformationActivity.class);
        startActivity(intent);
        break;
      case R.id.rl_setting_xc:
        //跳转个人行程
        Toast.makeText(getContext(), "个人行程页面", Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(getActivity(), DetailsPageActivity.class);
        startActivity(intent1);
        break;
      case R.id.rl_setting_tz:
        //跳转通知
        Toast.makeText(getContext(), "通知页面", Toast.LENGTH_SHORT).show();
        break;
      case R.id.rl_setting_yq:
        //跳转邀请好友
        Toast.makeText(getContext(), "邀请好友页面", Toast.LENGTH_SHORT).show();
        break;
      case R.id.rl_setting_fb:
        //跳转发布房源
        Toast.makeText(getContext(), "发布房源页面", Toast.LENGTH_SHORT).show();
        break;
      case R.id.rl_setting_sz:
        //跳转个人设置
        Toast.makeText(getContext(), "个人设置页面", Toast.LENGTH_SHORT).show();
        break;
      case R.id.rl_setting_hq:
        //跳转获取帮助
        Toast.makeText(getContext(), "获取帮助页面", Toast.LENGTH_SHORT).show();
        break;
      case R.id.rl_setting_fk:
        //跳转反馈页面
        Toast.makeText(getContext(), "反馈页面", Toast.LENGTH_SHORT).show();
        break;
    }
  }
}
