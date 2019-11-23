package com.example.mary.graduationproject.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.View.CircleImageView;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.event.wishlistEvent;
import com.example.mary.graduationproject.utils.PicassoUtils;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

public class DetailsPageActivity extends AppCompatActivity implements View.OnClickListener {

    public static String TAG = "DetailsPageActivity";
    private static Banner banner;
    private ArrayList<String> list_path = new ArrayList<>();
    private ImageView tool_image1, tool_image2, iv_house_loacation_map1;
    private CircleImageView iv_house_user;
    private TextView tv_location_house, tv_description_house, tv_house_xiangqing, tv_house_jianjie,
            tv_house_lookxq, tv_house_pingjia, tv_house_pinglun, tv_house_lookpj, tv_house_location,
            tv_house_location_message, tv_house_location_message1, tv_house_lookxq1, tv_house_locationcx1,
            tv_house_lookxq2, tv_everyday_money;
    private Button btn_book_room;
    private String house_location;
    private String Travel;
    String Introduction, Evaluation;
    private houseItemBean data = new houseItemBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);
        initView();
    }

    private void initView() {
        Intent intent = getIntent();
        String city_house_type = intent.getStringExtra("city_house_type");
        String city_house_feature = intent.getStringExtra("city_house_feature");
        String city_house_image = intent.getStringExtra("city_house_image");
        String city_house_price = intent.getStringExtra("city_house_price");
        String image2 = intent.getStringExtra("image2");
        String image0 = intent.getStringExtra("image0");
        String avatar = intent.getStringExtra("avatar");
        Evaluation = intent.getStringExtra("Evaluation");
        String image3 = intent.getStringExtra("image3");
        String image_map = intent.getStringExtra("image_map");
        Introduction = intent.getStringExtra("Introduction");
        Travel = intent.getStringExtra("Travel");
        String image1 = intent.getStringExtra("image1");
        house_location = intent.getStringExtra("house_location");

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

        //轮播图
        list_path.add(image0);
        list_path.add(image1);
        list_path.add(image2);
        list_path.add(image3);
        banner = findViewById(R.id.bn_banner);
        banner.setImageLoader(new MyLoader())
                .setImages(list_path)
                .setBannerAnimation(Transformer.Default)
                .setDelayTime(5000)
                .isAutoPlay(true)
                .setIndicatorGravity(BannerConfig.CENTER)
                .start();

        //返回
        tool_image1 = findViewById(R.id.tool_image1);
        tool_image1.setOnClickListener(this);
        //心动
        tool_image2 = findViewById(R.id.tool_image2);
        tool_image2.setOnClickListener(this);

        //地图
        iv_house_loacation_map1 = findViewById(R.id.iv_house_loacation_map1);
        Picasso.with(this).load(image_map).into(iv_house_loacation_map1);
        if (!TextUtils.isEmpty(image_map)) {
            //加载图片
            PicassoUtils.loadImageView(this, image_map, iv_house_loacation_map1);
        }
        iv_house_loacation_map1.setOnClickListener(this);

        //头像
        iv_house_user = findViewById(R.id.iv_house_user);
        iv_house_user.setOnClickListener(this);
        //位置 例如：整套公寓·上海
        tv_location_house = findViewById(R.id.tv_location_house);
        tv_location_house.setText(city_house_type);
        tv_location_house.setOnClickListener(this);
        //房屋描述
        tv_description_house = findViewById(R.id.tv_description_house);
        tv_description_house.setText(city_house_feature);
        tv_description_house.setOnClickListener(this);
        //详情标题
        tv_house_xiangqing = findViewById(R.id.tv_house_xiangqing);
        tv_house_xiangqing.setOnClickListener(this);
        //简介
        tv_house_jianjie = findViewById(R.id.tv_house_jianjie);
        tv_house_jianjie.setText(Introduction);
        tv_house_jianjie.setOnClickListener(this);
        //简介的查看详情
        tv_house_lookxq = findViewById(R.id.tv_house_lookxq);
        tv_house_lookxq.setOnClickListener(this);
        //评价标题
        tv_house_pingjia = findViewById(R.id.tv_house_pingjia);
        tv_house_pingjia.setOnClickListener(this);
        //评论
        tv_house_pinglun = findViewById(R.id.tv_house_pinglun);
        tv_house_pinglun.setText(Evaluation);
        tv_house_pinglun.setOnClickListener(this);
        //查看评价
        tv_house_lookpj = findViewById(R.id.tv_house_lookpj);
        tv_house_lookpj.setOnClickListener(this);
        //房屋地点位置
        tv_house_location = findViewById(R.id.tv_house_location);
        tv_house_location.setOnClickListener(this);
        //房屋位置描述
        tv_house_location_message = findViewById(R.id.tv_house_location_message);
        tv_house_location_message.setOnClickListener(this);

        tv_house_location_message1 = findViewById(R.id.tv_house_location_message1);
        tv_house_location_message1.setText(house_location);
        tv_house_location_message1.setOnClickListener(this);

        tv_house_lookxq1 = findViewById(R.id.tv_house_lookxq1);
        tv_house_lookxq1.setOnClickListener(this);

        tv_house_locationcx1 = findViewById(R.id.tv_house_locationcx1);
        tv_house_locationcx1.setText(Travel);
        tv_house_locationcx1.setOnClickListener(this);

        tv_house_lookxq2 = findViewById(R.id.tv_house_lookxq2);
        tv_house_lookxq2.setOnClickListener(this);
        //价格
        tv_everyday_money = findViewById(R.id.tv_everyday_money);
        tv_everyday_money.setText(city_house_price + "/夜");
        tv_everyday_money.setOnClickListener(this);
        //预定按钮
        btn_book_room = findViewById(R.id.btn_book_room);
        btn_book_room.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tool_image1:
                finish();
                break;
            case R.id.tool_image2:
                tool_image2.setImageResource(R.mipmap.circile_xin_fen);
                EventBus.getDefault().post(new wishlistEvent(data));
                break;
            case R.id.iv_house_loacation_map1:
                Toast.makeText(this, "您点击了地图", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_house_lookxq:
                //房屋介绍详情
                Intent intent = new Intent(this, HouseintroductionActivity.class);
                intent.putExtra("xiangqing", Introduction);
                startActivity(intent);
                break;
            case R.id.tv_house_lookpj:
                //房屋评论
                Intent intent1 = new Intent(this, HouseEcaluationActivity.class);
                intent1.putExtra("pinglun", Evaluation);
                startActivity(intent1);
                break;
            case R.id.tv_house_lookxq1:
                //房屋位置详情
                Intent intent2 = new Intent(this, HouseLocationActivity.class);
                intent2.putExtra("name", "房源位置");
                intent2.putExtra("description", house_location);
                startActivity(intent2);
                break;
            case R.id.tv_house_lookxq2:
                //房屋位置详情
                Intent intent3 = new Intent(this, HouseLocationActivity.class);
                intent3.putExtra("name", "出行交通");
                intent3.putExtra("description", Travel);
                startActivity(intent3);
                break;
            case R.id.btn_book_room:
                Intent intent4 = new Intent(this, bookRoomActivity.class);
                intent4.putExtra("House_location", data.getHouse_location());
                intent4.putExtra("Image_map", data.getImage_map());
                intent4.putExtra("City_house_price", data.getCity_house_price());
                intent4.putExtra("City_house_type", data.getCity_house_type());
                intent4.putExtra("City_house_feature", data.getCity_house_feature());
                startActivity(intent4);
                break;
        }
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load((String) path).into(imageView);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
