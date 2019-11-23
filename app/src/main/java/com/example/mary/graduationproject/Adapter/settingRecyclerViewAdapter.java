package com.example.mary.graduationproject.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.View.CircleImageView;
import com.example.mary.graduationproject.bean.settingItemBean;

import java.util.List;

/**
 * 创建时间：2019/2/20
 * 作者： Mary
 * 描述： '我的'页面 recycleView的adapter
 */

public class settingRecyclerViewAdapter extends RecyclerView.Adapter<settingRecyclerViewAdapter.MyViewHolder>{

  //当前上下文对象
  Context context;
  //RecyclerView填充Item数据的List对象
  List<settingItemBean> mList;

  public settingRecyclerViewAdapter(Context context,List<settingItemBean> List) {
    this.context = context;
    this.mList = List;
  }

  //创建ViewHolder
  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
      int viewType) {
    //实例化得到Item布局文件的View对象
    View v = View.inflate(context, R.layout.setting_recyclerview_item,null);
    //返回MyViewHolder的对象
    return new MyViewHolder(v);
  }

  //绑定数据
  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder,
      int position) {
    holder.mTextView.setText(mList.get(position).mTextView);
    holder.mImageView.setImageResource(mList.get(position).mImageView);
  }

  //返回item的数量
  @Override
  public int getItemCount() {
    return mList.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder{

    private TextView mTextView;
    private CircleImageView mImageView;

    @SuppressLint("WrongViewCast")
    public MyViewHolder(View itemView) {
      super(itemView);
      mTextView = itemView.findViewById(R.id.tv_setting_head);
      mImageView = itemView.findViewById(R.id.iv_setting_head);
    }
  }
}
