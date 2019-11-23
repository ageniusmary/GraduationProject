package com.example.mary.graduationproject.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mary.graduationproject.R;

/**
 * 创建时间：2019.2.10
 * 作者：Mary
 * 描述：个人信息界面
 */
public class PersonalinformationFragment extends Fragment {




  public PersonalinformationFragment(){

  }

  public static PersonalinformationFragment getInstance(){
    PersonalinformationFragment personalinformationFragment = new PersonalinformationFragment();
    return personalinformationFragment;
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = (View) inflater.inflate(R.layout.fragment_personalinformation,null);
    findView(view);
    return view;
  }

  private void findView(View view) {
  }
}
