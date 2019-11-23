package com.example.mary.graduationproject.Fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.example.mary.graduationproject.MainActivity;
import com.example.mary.graduationproject.R;
import org.greenrobot.eventbus.EventBus;

/**
 * 创建时间：2019.2.10
 * 作者： Mary
 * 描述： 收件箱fragment
 */

public class receiveFragment extends Fragment {


  private RelativeLayout Rl_receive_null;
  private Button btn_begin_explore;


  public static receiveFragment getInstance(){
    receiveFragment receiveFragment = new receiveFragment();
    return receiveFragment;
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_receive,null);
    findView(view);
    return view;
  }

  private void findView(View view) {
    Rl_receive_null = view.findViewById(R.id.Rl_receive_null);
    btn_begin_explore = view.findViewById(R.id.btn_begin_explore);
    //btn_begin_explore.setOnClickListener(new View.OnClickListener() {
    //  @Override
    //  public void onClick(View v) {
    //    getActivity().getSupportFragmentManager()
    //        .beginTransaction()
    //        .replace(R.id.fragment_container, sCanFragment.getInstance())
    //        .addToBackStack(null)
    //        .commit();
    //   ((MainActivity)getActivity()).setRadioGroup();
    //  }
    //});
    btn_begin_explore.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Activity activity = (MainActivity)getActivity();
        ((MainActivity) activity).setCurrentIndex(0);
      }
    });

  }
}
