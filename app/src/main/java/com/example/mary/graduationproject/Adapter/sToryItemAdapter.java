package com.example.mary.graduationproject.Adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.sToryItemDataBean;
import com.example.mary.graduationproject.utils.PicassoUtils;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * 项目名:    GraduationProject
 * 包名：     com.example.mary.graduationproject.Adapter
 * 创建者：   Mary
 * 创建时间:  2019/4/15 17:04
 * 描述：     TODO
 */
public class sToryItemAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater inflater;
    private List<sToryItemDataBean> mList;
    private sToryItemDataBean data;
    private int width,height;
    private WindowManager wm;

    public sToryItemAdapter(Context mContext, List<sToryItemDataBean> mList){
        this.mContext = mContext;
        this.mList = mList;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.activity_story_item, null);
            viewHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tv_description = (TextView) convertView.findViewById(R.id.tv_source);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        data = mList.get(position);
        viewHolder.tv_title.setText(data.getTitle());
        viewHolder.tv_description.setText(data.getDescription());
        Picasso.with(mContext).load(data.getImgUrl()).into(viewHolder.iv_img);
        if(!TextUtils.isEmpty(data.getImgUrl())){
            //加载图片
            PicassoUtils.loadImageViewSize(mContext, data.getImgUrl(), width/3, 250, viewHolder.iv_img);
        }

        return convertView;
    }

    class ViewHolder {
        private ImageView iv_img;
        private TextView tv_title;
        private TextView tv_description;
    }
}
