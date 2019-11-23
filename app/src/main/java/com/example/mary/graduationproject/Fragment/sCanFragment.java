package com.example.mary.graduationproject.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mary.graduationproject.Activity.DetailsPageActivity;
import com.example.mary.graduationproject.Activity.SearchActivity;
import com.example.mary.graduationproject.Activity.WebViewActivity;
import com.example.mary.graduationproject.Adapter.CardPagerAdapter;
import com.example.mary.graduationproject.Adapter.ShadowTransformer;
import com.example.mary.graduationproject.Adapter.scanItemCityRv1Adapter;
import com.example.mary.graduationproject.Adapter.scanItemCityRv3Adapter;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.CardItem;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.bean.sCanHouseItemBean;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * 创建时间：2019.2.10
 * 作者： Mary
 * 描述： 主fragment
 */

public class sCanFragment extends Fragment implements View.OnClickListener, scanItemCityRv1Adapter.OnItemClickListener, scanItemCityRv3Adapter.OnItemClickListener {

    public static final String TAG = "sCanFragment";
    private Toolbar mToolbar;
    private ScrollView mScrollView;
    private SearchView mSearchView;
    private RecyclerView scan_item_city_rv1;
    private RecyclerView scan_item_city_rv3;
    private scanItemCityRv1Adapter scanItemCityRv1Adapter;
    private scanItemCityRv3Adapter scanItemCityRv3Adapter;
    private Button scan_item_city_shanghai;
    private Button scan_item_city_chengdu;
    private Button scan_item_city_beijing;
    private Button scan_item_city_xian;
    private Button btn_show_more_house;
    private Button btn_show_more_tiyan;
    private static Banner banner;
    private ArrayList<String> list_path = new ArrayList<>();
    private ArrayList<houseItemBean> mListBean = new ArrayList<>();
    private ArrayList<sCanHouseItemBean> mListItem = new ArrayList<>();
    private ArrayList<houseItemBean> mListBean2 = new ArrayList<>();
    private ArrayList<sCanHouseItemBean> mListItem2 = new ArrayList<>();
    private ViewPager mViewPager;
    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;

    public sCanFragment() {
    }

    public static sCanFragment getInstance() {
        sCanFragment sCanFragment = new sCanFragment();
        return sCanFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scan, null);
        findView(view);
        String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
        initData(url);
        String url2 = "http://39.107.122.183:8080/house/list?random=true";
        initData2(url2);
        return view;
    }

    private void initData(String url) {
        //网络请求获取recyclerview数据
        //String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
        String URL = url;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parsingJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "失败  :" + error, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void parsingJson(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonList = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject json = (JSONObject) jsonList.get(i);
                sCanHouseItemBean itemBean = new sCanHouseItemBean();
                houseItemBean data = new houseItemBean();

                String city_house_type = json.getString("city_house_type");
                String city_house_feature = json.getString("city_house_feature");
                String city_house_image = json.getString("city_house_image");
                String city_house_price = json.getString("city_house_price");
                String image2 = json.getString("image2");
                String image0 = json.getString("image0");
                String avatar = json.getString("avatar");
                String Evaluation = json.getString("Evaluation");
                String image3 = json.getString("image3");
                String image_map = json.getString("image_map");
                String Introduction = json.getString("Introduction");
                String Travel = json.getString("Travel");
                String image1 = json.getString("image1");
                String house_location = json.getString("house_location");

                itemBean.setCity_house_type(city_house_type);
                itemBean.setCity_house_feature(city_house_feature);
                itemBean.setCity_house_image(city_house_image);
                itemBean.setCity_house_price(city_house_price);
                mListItem.add(itemBean);

                data.setAvatar(avatar);
                data.setCity_house_type(city_house_type);
                data.setCity_house_feature(city_house_feature);
                data.setCity_house_image(city_house_image);
                data.setCity_house_price(city_house_price);
                data.setImage0(image0);
                data.setImage1(image1);
                data.setImage2(image2);
                data.setImage3(image3);
                data.setEvaluation(Evaluation);
                data.setImage_map(image_map);
                data.setIntroduction(Introduction);
                data.setTravel(Travel);
                data.setHouse_location(house_location);
                mListBean.add(data);
            }
            scanItemCityRv1Adapter = new scanItemCityRv1Adapter(getActivity(), mListItem, mListBean);
            scanItemCityRv1Adapter.setOnItemClickListener(this);
            scan_item_city_rv1.setAdapter(scanItemCityRv1Adapter);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    scanItemCityRv1Adapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void findView(View view) {

        list_path.add("http://n2-q.mafengwo.net/s13/M00/32/36/wKgEaVxm60-AbEgTABGtbAXWWT424.jpeg?imageMogr2%2Fstrip");
        list_path.add("http://p3-q.mafengwo.net/s13/M00/7C/4E/wKgEaVyDnAKADbdWAAZIRXm5iuU07.jpeg?imageMogr2%2Fstrip");
        list_path.add("http://b4-q.mafengwo.net/s13/M00/75/80/wKgEaVy-35uAOef_ABZqP_MyrCE04.jpeg?imageMogr2%2Fstrip");
        list_path.add("http://n1-q.mafengwo.net/s13/M00/D6/6C/wKgEaVy_CDqAcQJFAA4n8ekLVDQ31.jpeg?imageMogr2%2Fstrip");
        mToolbar = view.findViewById(R.id.tl_toolbar);
        mSearchView = view.findViewById(R.id.sv_search);
        //mSearchView的点击事件
        mScrollView = view.findViewById(R.id.scrollView);

        scan_item_city_rv1 = view.findViewById(R.id.scan_item_city_rv1);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        scan_item_city_rv1.setLayoutManager(manager);
        scan_item_city_rv1.setNestedScrollingEnabled(false);

        mViewPager = view.findViewById(R.id.viewPager);
        mCardAdapter = new CardPagerAdapter(getActivity());
        mCardAdapter.addCardItem(new CardItem("冰岛", R.mipmap.bingddao));
        mCardAdapter.addCardItem(new CardItem("南美", R.mipmap.nanmei));
        mCardAdapter.addCardItem(new CardItem("秘鲁", R.mipmap.bilu));
        mCardAdapter.addCardItem(new CardItem("新加坡", R.mipmap.xinjiapo));
        mCardAdapter.addCardItem(new CardItem("乌尤尼盐沼", R.mipmap.yanzhao));
        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);
        mCardShadowTransformer.enableScaling(true);

        scan_item_city_rv3 = view.findViewById(R.id.scan_item_city_rv3);
        GridLayoutManager manager3 = new GridLayoutManager(getContext(), 2);
        manager3.setOrientation(GridLayoutManager.VERTICAL);
        scan_item_city_rv3.setLayoutManager(manager3);
        //scan_item_city_rv3.setNestedScrollingEnabled(false);
        scan_item_city_rv3.setAdapter(scanItemCityRv3Adapter);

        scan_item_city_shanghai = view.findViewById(R.id.scan_item_city_shanghai);
        scan_item_city_shanghai.setOnClickListener(this);
        scan_item_city_chengdu = view.findViewById(R.id.scan_item_city_chengdu);
        scan_item_city_chengdu.setOnClickListener(this);
        scan_item_city_beijing = view.findViewById(R.id.scan_item_city_beijing);
        scan_item_city_beijing.setOnClickListener(this);
        scan_item_city_xian = view.findViewById(R.id.scan_item_city_xian);
        scan_item_city_xian.setOnClickListener(this);
        btn_show_more_house = view.findViewById(R.id.btn_show_more_house);
        btn_show_more_house.setOnClickListener(this);
        btn_show_more_tiyan = view.findViewById(R.id.btn_show_more_tiyan);
        btn_show_more_tiyan.setOnClickListener(this);

        //轮播图的设置
        banner = view.findViewById(R.id.banner);
        banner.setImageLoader(new MyLoader())
                .setImages(list_path)
                .setBannerAnimation(Transformer.Default)
                .setDelayTime(5000)
                .isAutoPlay(true)
                .setIndicatorGravity(BannerConfig.CENTER)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        switch (position) {
                            case 0:
                                //Toast.makeText(getContext(), "你点击了0", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                                intent.putExtra("title", "【旅行手记】尼泊尔七日见闻，与信仰相伴，与神明共舞");
                                intent.putExtra("url", "http://39.107.122.183:8080/story/1.htm");
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getActivity(), WebViewActivity.class);
                                intent1.putExtra("title", "MINI海南环岛-错过的日出与夕阳，没去成的海滩与高山");
                                intent1.putExtra("url", "http://39.107.122.183:8080/story/2.htm");
                                startActivity(intent1);
                                break;
                            case 2:
                                Intent intent2 = new Intent(getActivity(), WebViewActivity.class);
                                intent2.putExtra("title", "硝烟之后的血与蜜之地｜塞尔维亚和波黑的九天八夜");
                                intent2.putExtra("url", "http://39.107.122.183:8080/story/3.htm");
                                startActivity(intent2);
                                break;
                            case 3:
                                Intent intent3 = new Intent(getActivity(), WebViewActivity.class);
                                intent3.putExtra("title", "远辰游世界|跟着太阳流浪，在欧洲大陆最西边与葡萄牙相遇");
                                intent3.putExtra("url", "http://39.107.122.183:8080/story/4.htm");
                                startActivity(intent3);
                                break;
                        }
                    }
                }).start();

        mSearchView.setQueryHint("请输入\"上海\"试试～");
        mSearchView.setIconifiedByDefault(false);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            //当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                switch (newText) {
                    case "北京":
                        Intent intent = new Intent(getActivity(), SearchActivity.class);
                        intent.putExtra("cityName","北京");
                        startActivity(intent);
                        break;
                    case "西安":
                        Intent intent1 = new Intent(getActivity(),SearchActivity.class);
                        intent1.putExtra("cityName","西安");
                        startActivity(intent1);
                        break;
                    case "上海":
                        Intent intent2 = new Intent(getActivity(),SearchActivity.class);
                        intent2.putExtra("cityName","上海");
                        startActivity(intent2);
                        break;
                    case "成都":
                        Intent intent3 = new Intent(getActivity(),SearchActivity.class);
                        intent3.putExtra("cityName","成都");
                        startActivity(intent3);
                        break;
                }
                return false;
            }
        });

        //去掉下划线
        mSearchView.findViewById(android.support.v7.appcompat.R.id.search_plate).setBackground(null);
        mSearchView.findViewById(android.support.v7.appcompat.R.id.submit_area).setBackground(null);

        //在xml文件里 searchView的父控件里加这句 可以取消焦点 android:focusableInTouchMode="true"

        //设置toolbar初始透明度为0
        mToolbar.getBackground().mutate().setAlpha(0);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(
                new ViewTreeObserver.OnScrollChangedListener() {
                    @Override
                    public void onScrollChanged() {
                        //改变toolbar的透明度
                        changeToolbarAlpha();
                    }
                });

    }

    private void changeToolbarAlpha() {
        int scrollY = mScrollView.getScrollY();
        //快速下拉会引起瞬间scrollY<0
        if (scrollY < 0) {
            mToolbar.getBackground().mutate().setAlpha(0);
            return;
        }
        //计算当前透明度比率
        float radio = Math.min(1, scrollY / (banner.getHeight() - mToolbar.getHeight() * 1f));
        //设置透明度
        mToolbar.getBackground().mutate().setAlpha((int) (radio * 0xFF));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.scan_item_city_shanghai:
                //Toast.makeText(getContext(), "点击了上海", Toast.LENGTH_SHORT).show();
                //scanItemCityRv1Adapter = new scanItemCityRv1Adapter(getActivity(), null,null);
                mListItem.clear();
                mListBean.clear();
                String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
                initData(url);
                break;
            case R.id.scan_item_city_beijing:
                //Toast.makeText(getContext(), "点击了北京", Toast.LENGTH_SHORT).show();
                mListItem.clear();
                mListBean.clear();
                String url1 = "http://39.107.122.183:8080/house/list?city=%E5%8C%97%E4%BA%AC&page=1&limit=6";
                initData(url1);
                btn_show_more_house.setText("显示更多北京房源");
                break;
            case R.id.scan_item_city_xian:
                //Toast.makeText(getContext(), "点击了西安", Toast.LENGTH_SHORT).show();
                mListItem.clear();
                mListBean.clear();
                String url2 = "http://39.107.122.183:8080/house/list?city=%E8%A5%BF%E5%AE%89&page=1&limit=6";
                initData(url2);
                btn_show_more_house.setText("显示更多西安房源");
                break;
            case R.id.scan_item_city_chengdu:
                //Toast.makeText(getContext(), "点击了成都", Toast.LENGTH_SHORT).show();
                mListItem.clear();
                mListBean.clear();
                String url3 = "http://39.107.122.183:8080/house/list?city=%E6%88%90%E9%83%BD&page=1&limit=6";
                initData(url3);
                btn_show_more_house.setText("显示更多成都房源");
                break;
            case R.id.btn_show_more_house:
                Toast.makeText(getContext(), "点击了显示更多", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_show_more_tiyan:
                Toast.makeText(getContext(), "点击了体验", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View parent, int position) {
        Intent intent = new Intent(getActivity(), DetailsPageActivity.class);
        intent.putExtra("city_house_type", mListBean.get(position).getCity_house_type());
        intent.putExtra("city_house_feature", mListBean.get(position).getCity_house_feature());
        intent.putExtra("city_house_image", mListBean.get(position).getCity_house_image());
        intent.putExtra("city_house_price", mListBean.get(position).getCity_house_price());
        intent.putExtra("image2", mListBean.get(position).getImage2());
        intent.putExtra("image0", mListBean.get(position).getImage0());
        intent.putExtra("avatar", mListBean.get(position).getAvatar());
        intent.putExtra("Evaluation", mListBean.get(position).getEvaluation());
        intent.putExtra("image3", mListBean.get(position).getImage3());
        intent.putExtra("image_map", mListBean.get(position).getImage_map());
        intent.putExtra("Introduction", mListBean.get(position).getIntroduction());
        intent.putExtra("Travel", mListBean.get(position).getTravel());
        intent.putExtra("image1", mListBean.get(position).getImage1());
        intent.putExtra("house_location", mListBean.get(position).getHouse_location());
        startActivity(intent);
    }

    @Override
    public void onClick3(View parent, int position) {
        Intent intent = new Intent(getActivity(), DetailsPageActivity.class);
        intent.putExtra("city_house_type", mListBean2.get(position).getCity_house_type());
        intent.putExtra("city_house_feature", mListBean2.get(position).getCity_house_feature());
        intent.putExtra("city_house_image", mListBean2.get(position).getCity_house_image());
        intent.putExtra("city_house_price", mListBean2.get(position).getCity_house_price());
        intent.putExtra("image2", mListBean2.get(position).getImage2());
        intent.putExtra("image0", mListBean2.get(position).getImage0());
        intent.putExtra("avatar", mListBean2.get(position).getAvatar());
        intent.putExtra("Evaluation", mListBean2.get(position).getEvaluation());
        intent.putExtra("image3", mListBean2.get(position).getImage3());
        intent.putExtra("image_map", mListBean2.get(position).getImage_map());
        intent.putExtra("Introduction", mListBean2.get(position).getIntroduction());
        intent.putExtra("Travel", mListBean2.get(position).getTravel());
        intent.putExtra("image1", mListBean2.get(position).getImage1());
        intent.putExtra("house_location", mListBean2.get(position).getHouse_location());
        startActivity(intent);
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    private void initData2(String url) {
        //网络请求获取recyclerview数据
        //String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
        String URL = url;
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parsingJson2(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "失败  :" + error, Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void parsingJson2(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonList = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonList.length(); i++) {
                JSONObject json = (JSONObject) jsonList.get(i);
                sCanHouseItemBean itemBean = new sCanHouseItemBean();
                houseItemBean data = new houseItemBean();

                String city_house_type = json.getString("city_house_type");
                String city_house_feature = json.getString("city_house_feature");
                String city_house_image = json.getString("city_house_image");
                String city_house_price = json.getString("city_house_price");
                String image2 = json.getString("image2");
                String image0 = json.getString("image0");
                String avatar = json.getString("avatar");
                String Evaluation = json.getString("Evaluation");
                String image3 = json.getString("image3");
                String image_map = json.getString("image_map");
                String Introduction = json.getString("Introduction");
                String Travel = json.getString("Travel");
                String image1 = json.getString("image1");
                String house_location = json.getString("house_location");

                itemBean.setCity_house_type(city_house_type);
                itemBean.setCity_house_feature(city_house_feature);
                itemBean.setCity_house_image(city_house_image);
                itemBean.setCity_house_price(city_house_price);
                mListItem2.add(itemBean);

                data.setAvatar(avatar);
                data.setCity_house_type(city_house_type);
                data.setCity_house_feature(city_house_feature);
                data.setCity_house_image(city_house_image);
                data.setCity_house_price(city_house_price);
                data.setImage0(image0);
                data.setImage1(image1);
                data.setImage2(image2);
                data.setImage3(image3);
                data.setEvaluation(Evaluation);
                data.setImage_map(image_map);
                data.setIntroduction(Introduction);
                data.setTravel(Travel);
                data.setHouse_location(house_location);
                mListBean2.add(data);
            }
            scanItemCityRv3Adapter = new scanItemCityRv3Adapter(getActivity(), mListItem2, mListBean2);
            scanItemCityRv3Adapter.setOnItemClickListener(this);
            scan_item_city_rv3.setAdapter(scanItemCityRv3Adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d(TAG, "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }
}
