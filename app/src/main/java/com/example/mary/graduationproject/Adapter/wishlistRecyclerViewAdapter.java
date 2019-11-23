package com.example.mary.graduationproject.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.bean.wishlistItemBean;
import com.example.mary.graduationproject.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 创建时间：2019/2/22
 * 作者： Mary
 * 描述：心愿单页面 recycleView的adapter
 */
public class wishlistRecyclerViewAdapter extends RecyclerView.Adapter<wishlistRecyclerViewAdapter.MyViewHolder> {
    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    List<houseItemBean> mList;
    private int width, height;
    private WindowManager wm;

    public wishlistRecyclerViewAdapter(Context context, List<houseItemBean> mList) {
        this.context = context;
        this.mList = mList;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.wishlist_recyclerview_item, null);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso.with(context).load(mList.get(position).getCity_house_image()).into(holder.imageView);
        if (!TextUtils.isEmpty(mList.get(position).getCity_house_image())) {
            //加载图片
            PicassoUtils.loadImageViewSize(context, mList.get(position).getCity_house_image(), width, height, holder.imageView);
        }
        holder.textView.setText(mList.get(position).getCity_house_feature());
        //holder.textView.setText(mList.get(position).mCity);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView textView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_wishlist_item);
            textView = itemView.findViewById(R.id.tv_wishlist_item);
        }
    }
}
