package com.example.capstone; // completed

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
        ImageView imgvTitle = (ImageView)findViewById(R.id.imgvTitle);
        ImageView imgvDoor = (ImageView)findViewById(R.id.imgvDoor);
        ImageView imgvTxt = (ImageView)findViewById(R.id.imgvTxt);
        final Animation animation1 = AnimationUtils.loadAnimation(this,
                R.anim.animationfortitle);
        final Animation animation2 = AnimationUtils.loadAnimation(this,
                R.anim.animationfortitle2);
        imgvTitle.startAnimation(animation1);
        imgvTitle.startAnimation(animation2);
        final Animation animation3 = AnimationUtils.loadAnimation(this,
                R.anim.animationfortext);
        imgvTxt.startAnimation(animation3);
        imgvDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity1Intro.this,
                        Activity2Login.class);
                startActivity(mIntent);
            }
        });
    }
}