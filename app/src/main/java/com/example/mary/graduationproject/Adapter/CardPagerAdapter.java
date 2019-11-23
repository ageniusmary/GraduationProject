package com.example.mary.graduationproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mary.graduationproject.Activity.WebViewActivity;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.CardItem;

import java.util.ArrayList;
import java.util.List;
/**
 * 创建时间：2019.4.28
 * 作者： Mary
 * 描述：
 */

public class CardPagerAdapter extends PagerAdapter implements CardAdapter {

    private List<CardView> mViews;
    private List<CardItem> mData;
    private float mBaseElevation;
    //当前上下文对象
    Context context;
    private ArrayList<String> list_path = new ArrayList<>();

    public CardPagerAdapter(FragmentActivity activity) {
        mData = new ArrayList<>();
        mViews = new ArrayList<>();
        context = activity;
        list_path.add("http://39.107.122.183:8080/story/4.htm");
        list_path.add("http://39.107.122.183:8080/story/9.htm");
        list_path.add("http://39.107.122.183:8080/story/3.htm");
        list_path.add("http://39.107.122.183:8080/story/2.htm");
        list_path.add("http://39.107.122.183:8080/story/11.htm");
    }

    public void addCardItem(CardItem item) {
        mViews.add(null);
        mData.add(item);
    }

    public float getBaseElevation() {
        return mBaseElevation;
    }

    @Override
    public CardView getCardViewAt(int position) {
        return mViews.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.adapter, container, false);
        container.addView(view);
        bind(mData.get(position), view);
        CardView cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("title", "景点旅游城市");
                intent.putExtra("url", list_path.get(position));
                context.startActivity(intent);
            }
        });
        if (mBaseElevation == 0) {
            mBaseElevation = cardView.getCardElevation();
        }

        cardView.setMaxCardElevation(mBaseElevation * MAX_ELEVATION_FACTOR);
        mViews.set(position, cardView);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        mViews.set(position, null);
    }

    private void bind(CardItem item, View view) {
//        TextView titleTextView = (TextView) view.findViewById(R.id.titleTextView);
//        TextView contentTextView = (TextView) view.findViewById(R.id.contentTextView);
//        titleTextView.setText(item.getTitle());
//        contentTextView.setText(item.getText());
        TextView mCityResource =(TextView) view.findViewById(R.id.titleTextView);
        LinearLayout mbackResource = view.findViewById(R.id.ll_background);
        mCityResource.setText(item.getTitle());
        mbackResource.setBackgroundResource(item.getText());
    }
}
