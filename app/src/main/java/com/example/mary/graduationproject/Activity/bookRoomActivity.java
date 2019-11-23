package com.example.mary.graduationproject.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mary.graduationproject.R;

public class bookRoomActivity extends AppCompatActivity {

    private String House_location;
    private String Image_map;
    private String City_house_price;
    private String City_house_type;
    private String City_house_feature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);
    }
}
