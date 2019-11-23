package com.example.mary.graduationproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mary.graduationproject.R;

public class HouseEcaluationActivity extends AppCompatActivity {

  private ImageView back;
  private TextView house_message;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_house_ecaluation);
    Intent intent = getIntent();
    house_message = findViewById(R.id.lv_pinglun);
    house_message.setText(intent.getStringExtra("pinglun"));

    back = findViewById(R.id.fangyuanpinglunback);
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}
