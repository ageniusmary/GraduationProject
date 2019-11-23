package com.example.mary.graduationproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.mary.graduationproject.R;
import com.example.mary.graduationproject.event.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class editNameActivity extends AppCompatActivity {

  private ImageView back;
  private TextView tv_save;
  private EditText et_content;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit_name);

    back = findViewById(R.id.fangyuanweizhiback);
    back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
    tv_save = findViewById(R.id.tv_save);
    et_content = findViewById(R.id.et_content);
    tv_save.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String phrase = et_content.getText().toString();
        EventBus.getDefault().post(new MessageEvent(phrase));
        finish();
      }
    });
  }
}
