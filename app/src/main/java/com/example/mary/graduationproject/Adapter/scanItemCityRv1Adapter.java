package com.example.mary.graduationproject.Adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.bean.sCanHouseItemBean;
import com.example.mary.graduationproject.bean.settingItemBean;
import com.example.mary.graduationproject.interfaceBean.OnItemClicklistener;
import com.example.mary.graduationproject.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间：2019/3/4
 * 作者：   Mary
 * 描述：
 */
public class scanItemCityRv1Adapter extends RecyclerView.Adapter<scanItemCityRv1Adapter.MyViewHolder> {

    //当前上下文对象
    Context context;
    //RecyclerView填充Item数据的List对象
    private ArrayList<sCanHouseItemBean> mListItem = null;
    private ArrayList<houseItemBean> mListBean;
    private int width, height;
    private WindowManager wm;
    private OnItemClickListener mOnItemClickListener = null;

    public scanItemCityRv1Adapter(FragmentActivity activity, ArrayList<sCanHouseItemBean> mListItem, ArrayList<houseItemBean> mListBean) {
        this.context = activity;
        this.mListItem = mListItem;
        this.mListBean = mListBean;
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.scan_item_city_rv1_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Picasso.with(context).load(mListItem.get(position).getCity_house_image()).into(holder.scan_item_house_image);
        if (!TextUtils.isEmpty(mListItem.get(position).getCity_house_image())) {
            //加载图片
            PicassoUtils.loadImageViewSize(context, mListItem.get(position).getCity_house_image(), width, height, holder.scan_item_house_image);
        }
        holder.scan_item_house_location.setText(mListItem.get(position).getCity_house_type());
        holder.scan_item_house_money.setText(mListItem.get(position).getCity_house_price() + "/夜");
        holder.scan_item_house_name.setText(mListItem.get(position).getCity_house_feature());


        holder.scan_item_house_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onClick(holder.scan_item_house_image, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mListItem.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView scan_item_house_image;
        public TextView scan_item_house_location;
        public TextView scan_item_house_name;
        public TextView scan_item_house_money;

        public MyViewHolder(View itemView) {
            super(itemView);
            scan_item_house_image = itemView.findViewById(R.id.scan_item_house_image);
            scan_item_house_location = itemView.findViewById(R.id.scan_item_house_location);
            scan_item_house_name = itemView.findViewById(R.id.scan_item_house_name);
            scan_item_house_money = itemView.findViewById(R.id.scan_item_house_money);
        }

    }

    // 点击事件接口
    public interface OnItemClickListener {
        void onClick(View parent, int position);
    }

    // 设置点击事件
    public void setOnItemClickListener(OnItemClickListener l) {
        this.mOnItemClickListener = l;
    }


}
