package com.example.mary.graduationproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mary.graduationproject.Adapter.scanItemCityRv1Adapter;
import com.example.mary.graduationproject.Adapter.wishlistRecyclerViewAdapter;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.bean.sCanHouseItemBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<houseItemBean> mList = new ArrayList<>();
    private wishlistRecyclerViewAdapter mAdatper;
    private RecyclerView mRecyclerView;
    private ArrayList<houseItemBean> mListBean = new ArrayList<>();
    private ArrayList<sCanHouseItemBean> mListItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initView();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv_search);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdatper = new wishlistRecyclerViewAdapter(this,mList);
        mRecyclerView.setAdapter(mAdatper);

        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");

        switch (cityName){
            case "北京":
                String url1 = "http://39.107.122.183:8080/house/list?city=%E5%8C%97%E4%BA%AC&page=1&limit=6";
                initData(url1);
                break;
            case "上海":
                String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
                initData(url);
                break;
            case "西安":
                String url2 = "http://39.107.122.183:8080/house/list?city=%E8%A5%BF%E5%AE%89&page=1&limit=6";
                initData(url2);
                break;
            case "成都":
                String url3 = "http://39.107.122.183:8080/house/list?city=%E6%88%90%E9%83%BD&page=1&limit=6";
                initData(url3);
                break;
        }
    }

    private void initData(String url) {
        //网络请求获取recyclerview数据
        //String url = "http://39.107.122.183:8080/house/list?city=%E4%B8%8A%E6%B5%B7&page=1&limit=6";
        String URL = url;
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                parsingJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplication(), "失败  :" + error, Toast.LENGTH_SHORT).show();
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mAdatper = new wishlistRecyclerViewAdapter(this,mListBean);
            mRecyclerView.setAdapter(mAdatper);
//            scanItemCityRv1Adapter = new scanItemCityRv1Adapter(getActivity(), mListItem, mListBean);
//            scanItemCityRv1Adapter.setOnItemClickListener(this);
//            scan_item_city_rv1.setAdapter(scanItemCityRv1Adapter);
//            getActivity().runOnUiThread(new Runnable() {
//                @Override
//                public void run() {
//                    scanItemCityRv1Adapter.notifyDataSetChanged();
//                }
//            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
