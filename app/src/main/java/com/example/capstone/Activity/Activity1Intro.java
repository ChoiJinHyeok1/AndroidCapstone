package com.example.capstone.Activity; // completed

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.capstone.R;

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
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            // 다음 화면으로 넘어갈 클래스 지정한다.
            Intent intent = new Intent(getApplicationContext(), Activity2Login.class);
            startActivity(intent);  // 다음 화면으로 넘어간다.
        }
        return super.onTouchEvent(event);
    }
}