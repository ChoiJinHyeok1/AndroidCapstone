package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Activity1Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro1);
        ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
        ImageView imageView3 = (ImageView)findViewById(R.id.imgv_TapToEnter);

    }
}