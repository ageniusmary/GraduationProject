package com.example.mary.graduationproject.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mary.graduationproject.Activity.WebViewActivity;
import com.example.mary.graduationproject.Adapter.sToryItemAdapter;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.sToryItemDataBean;
//import com.kymjs.rxvolley.RxVolley;
//import com.kymjs.rxvolley.client.HttpCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/2/18
 * 作者： Mary
 * 描述： 故事fragment
 */

public class sToryFragment extends Fragment {

    private ListView mListView;
    private List<sToryItemDataBean> mList = new ArrayList<>();
    //标题
    private List<String> mListTitle = new ArrayList<>();
    //地址
    private List<String> mListUrl = new ArrayList<>();

    public static sToryFragment getInstance() {
        sToryFragment sToryFragment = new sToryFragment();
        return sToryFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_story, null);
        findView(view);
        return view;
    }

    private void findView(View view) {
        mListView = view.findViewById(R.id.mListView);
        //String url = "http://api.tianapi.com/wxnew/?key=be4877cf3a4a8d0411c6b1bca0fd5eca&num=20";
        String url = "http://39.107.122.183:8080/Story/list";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
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
//        RxVolley.get(url, new HttpCallback() {
//            @Override
//            public void onSuccess(String t) {
//                parsingJson(t);
//            }
//        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("title", mListTitle.get(position));
                intent.putExtra("url", mListUrl.get(position));
                startActivity(intent);
            }
        });
    }

    private void parsingJson(String t){
        try{
            JSONObject jsonObject = new JSONObject(t);
            JSONArray jsonList = jsonObject.getJSONArray("newslist");
            for(int i=0;i<jsonList.length();i++){
                JSONObject json = (JSONObject) jsonList.get(i);
                sToryItemDataBean data = new sToryItemDataBean();

                String title = json.getString("title");
                String url = json.getString("url");

                data.setTitle(title);
                data.setDescription(json.getString("description"));
                data.setImgUrl(json.getString("picUrl"));

                mList.add(data);
                mListTitle.add(title);
                mListUrl.add(url);
            }
            sToryItemAdapter adapter = new sToryItemAdapter(getActivity(),mList);
            mListView.setAdapter(adapter);

        }catch (JSONException e){
            e.printStackTrace();
        }
    }

}
