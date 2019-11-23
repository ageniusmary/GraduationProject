package com.example.mary.graduationproject.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mary.graduationproject.R;

public class HouseLocationActivity extends AppCompatActivity {

  private ImageView back;
  private TextView name;
  private TextView description;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_house_location);

    Intent intent = getIntent();
    name = findViewById(R.id.tv_fangzi_location);
    name.setText(intent.getStringExtra("name"));
    description = findViewById(R.id.tv_description);
    description.setText(intent.getStringExtra("description"));
    back = findViewById(R.id.fangyuanweizhiback);
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }
}
