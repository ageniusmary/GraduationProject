package com.example.mary.graduationproject;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.mary.graduationproject.Fragment.*;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019.2.10
 * 作者： Mary
 * 描述： 主activity
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //public RadioGroup mTabRadioGroup;
    //public SparseArray<Fragment> mFragmentSparseArray;
    //private static final String TAG = "MainActivity";
    //
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //    setContentView(R.layout.activity_main);
    //    initView();
    //}
    //
    //private void initView() {
    //
    //    mFragmentSparseArray = new SparseArray<>();
    //    mFragmentSparseArray.append(R.id.scan_tab, sCanFragment.getInstance());
    //    mFragmentSparseArray.append(R.id.wishlist_tab, WishlistFragment.getInstance());
    //    mFragmentSparseArray.append(R.id.story_tab, sToryFragment.getInstance());
    //    mFragmentSparseArray.append(R.id.receive_tab, receiveFragment.getInstance());
    //    mFragmentSparseArray.append(R.id.settings_tab, settingFragment.getInstance());
    //
    //    mTabRadioGroup = findViewById(R.id.tabs_rg);
    //    mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
    //        @Override
    //        public void onCheckedChanged(RadioGroup group, int checkedId) {
    //            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
    //                mFragmentSparseArray.get(checkedId)).addToBackStack(null).commit();
    //        }
    //    });
    //
    //    //默认显示第一个
    //    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
    //        mFragmentSparseArray.get(R.id.scan_tab)).addToBackStack(null).commit();
    //
    //    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
    //    //    getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    //    //    ViewGroup rootView = (ViewGroup) ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
    //    //    ViewCompat.setFitsSystemWindows(rootView,false);
    //    //    rootView.setClipToPadding(true);
    //    //}
    //    //if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    //    //    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS|
    //    //        WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    //    //    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN| View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
    //    //    getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    //    //
    //    //    getWindow().setStatusBarColor(Color.TRANSPARENT);
    //    //}
    //
    //}
    //
    //public void setRadioGroup() {
    //    mTabRadioGroup.check(R.id.scan_tab);
    //}
    //
    private static final String CURRENT_FRAGMENT = "mary";
    private FragmentManager fragmentManager;
    private Fragment currentFragment = new Fragment();
    private List<Fragment> fragments = new ArrayList<>();
    public int currentIndex = 0;
    private RadioButton b1, b2, b3, b4, b5;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = findViewById(R.id.scan_tab);
        b2 = findViewById(R.id.wishlist_tab);
        b3 = findViewById(R.id.story_tab);
        b4 = findViewById(R.id.receive_tab);
        b5 = findViewById(R.id.settings_tab);

        fragmentManager = getSupportFragmentManager();

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);

        if (savedInstanceState != null) { // “内存重启”时调用

            //获取“内存重启”时保存的索引下标
            currentIndex = savedInstanceState.getInt(CURRENT_FRAGMENT, 0);

            //注意，添加顺序要跟下面添加的顺序一样！！！！
            fragments.removeAll(fragments);
            fragments.add(fragmentManager.findFragmentByTag(0 + ""));
            fragments.add(fragmentManager.findFragmentByTag(1 + ""));
            fragments.add(fragmentManager.findFragmentByTag(2 + ""));
            fragments.add(fragmentManager.findFragmentByTag(3 + ""));
            fragments.add(fragmentManager.findFragmentByTag(4 + ""));

            //恢复fragment页面
            restoreFragment();

        } else {      //正常启动时调用

            fragments.add(new sCanFragment());
            fragments.add(new WishlistFragment());
            fragments.add(new sToryFragment());
            fragments.add(new receiveFragment());
            fragments.add(new settingFragment());

            showFragment();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        //“内存重启”时保存当前的fragment名字
        outState.putInt(CURRENT_FRAGMENT, currentIndex);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_tab:
                currentIndex = 0;
                break;
            case R.id.wishlist_tab:
                currentIndex = 1;
                break;
            case R.id.story_tab:
                currentIndex = 2;
                break;
            case R.id.receive_tab:
                currentIndex = 3;
                break;
            case R.id.settings_tab:
                currentIndex = 4;
                break;
        }
        showFragment();

    }

    /**
     * 使用show() hide()切换页面
     * 显示fragment
     */
    private void showFragment() {

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        //如果之前没有添加过
        if (!fragments.get(currentIndex).isAdded()) {
            transaction
                    .hide(currentFragment)
                    .add(R.id.fragment_container, fragments.get(currentIndex), "" + currentIndex);  //第三个参数为添加当前的fragment时绑定一个tag

        } else {
            transaction
                    .hide(currentFragment)
                    .show(fragments.get(currentIndex));
        }

        currentFragment = fragments.get(currentIndex);

        transaction.commit();

    }

    /**
     * 恢复fragment
     */
    private void restoreFragment() {


        FragmentTransaction mBeginTreansaction = fragmentManager.beginTransaction();

        for (int i = 0; i < fragments.size(); i++) {

            if (i == currentIndex) {
                mBeginTreansaction.show(fragments.get(i));
            } else {
                mBeginTreansaction.hide(fragments.get(i));
            }

        }

        mBeginTreansaction.commit();

        //把当前显示的fragment记录下来
        currentFragment = fragments.get(currentIndex);

    }

    public void setCurrentIndex(int i) {
        currentIndex = i;
        b1.setChecked(true);
        showFragment();
    }
}
