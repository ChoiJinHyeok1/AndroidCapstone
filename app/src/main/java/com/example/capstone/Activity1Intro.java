package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Activity1Intro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1_intro);
        ImageView imageView1 = (ImageView)findViewById(R.id.imgv_Title);
        ImageView imageView3 = (ImageView)findViewById(R.id.imgv_TapToEnter);
        final Animation animation = AnimationUtils.loadAnimation(this, R.anim.translate);
        imageView1.startAnimation(animation);
    }
}