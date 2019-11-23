package com.example.mary.graduationproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mary.graduationproject.R;

/**
 * 创建时间：2019/3/4
 * 作者：Mary
 * 描述：
 */
public class scanItemCityRv2Adapter extends RecyclerView.Adapter<scanItemCityRv2Adapter.MyHolderView>{

  //当前上下文对象
  Context context;

  @NonNull
  @Override
  public MyHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.scan_item_city_rv2_item,parent,false);
    return new MyHolderView(view);
  }

  @Override
  public void onBindViewHolder(@NonNull MyHolderView holder, int position) {
    //holder.imageView.setImageResource();
    //holder.textView_city.setText();
    //holder.textView_money.setText();
  }

  @Override
  public int getItemCount() {
    return 0;
  }

  class MyHolderView extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView textView_city;
    public TextView textView_money;

    public MyHolderView(View itemView) {
      super(itemView);
      imageView = itemView.findViewById(R.id.scan_item_city_iv2);
      textView_city = itemView.findViewById(R.id.scan_item_city_tv2);
      textView_money = itemView.findViewById(R.id.scan_item_city_tv_money);
    }
  }
}
