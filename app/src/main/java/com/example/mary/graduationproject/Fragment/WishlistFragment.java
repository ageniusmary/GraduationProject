package com.example.mary.graduationproject.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mary.graduationproject.Adapter.wishlistRecyclerViewAdapter;
import com.example.mary.graduationproject.MainActivity;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.bean.houseItemBean;
import com.example.mary.graduationproject.bean.wishlistItemBean;
import com.example.mary.graduationproject.event.MessageEvent;
import com.example.mary.graduationproject.event.wishlistEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

/**
 * 创建时间：2019.2.18
 * 作者： Mary
 * 描述：心愿单fragment
 */

public class WishlistFragment extends Fragment implements View.OnClickListener{

  private LinearLayout linearLayout;
  private TextView textView;
  private RecyclerView mRecyclerView;
  private houseItemBean data ;
  private List<houseItemBean> mList = new ArrayList<>();
  private wishlistRecyclerViewAdapter mAdatper;

  public static WishlistFragment getInstance(){
    WishlistFragment wishlistFragment = new WishlistFragment();
    return wishlistFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = (View) inflater.inflate(R.layout.fragment_wishlist,null);
    findView(view);
    EventBus.getDefault().register(this);
    return view;
  }

  private void findView(View view) {
//    data = new houseItemBean();
//    data.setCity_house_image("http://39.107.122.183:8080/image/shanghai/1/house.png");
//    data.setCity_house_feature("【襄阳南路】地铁房/田子坊/日月光/通透整洁大床房");
//   mList.add(data);
    linearLayout = view.findViewById(R.id.ll_wishlist_message_show);
    textView = view.findViewById(R.id.tv_wishlist_begin_explore);
    textView.setOnClickListener(this);
    mRecyclerView = view.findViewById(R.id.rv_wishlist);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mAdatper = new wishlistRecyclerViewAdapter(getContext(),mList);
    mRecyclerView.setAdapter(mAdatper);
  }

  @Override
  public void onClick(View v) {
    //switch (v.getId()){
      //case R.id.tv_wishlist_begin_explore:
      //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
      //    sCanFragment.getInstance()).addToBackStack(null).commit();
      //  ((MainActivity)getActivity()).setRadioGroup();
      //  break;
    //}
    Activity activity = (MainActivity)getActivity();
    ((MainActivity) activity).setCurrentIndex(0);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onMessagesEvent(wishlistEvent event){
    this.data = event.getData();
    mList.add(event.getData());
    mAdatper.notifyDataSetChanged();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }
}
