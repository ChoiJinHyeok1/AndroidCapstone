package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.capstone.Activity.Activity5Main;

public class Waitingforapproval extends AppCompatActivity {
    ImageView imgvTitle1, imgvTitle2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitingforapproval);
        imgvTitle1 = findViewById(R.id.imgvActWTitle1);
        imgvTitle2 = findViewById(R.id.imgvActWTitle2);
        final Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.animationfortext2);
        imgvTitle1.startAnimation(animation);
        imgvTitle2.startAnimation(animation);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        if (action == MotionEvent.ACTION_DOWN) {
            // 다음 화면으로 넘어갈 클래스 지정한다.
            Intent intent = new Intent(getApplicationContext(), Activity5Main.class);
            startActivity(intent);  // 다음 화면으로 넘어간다.
        }
        return super.onTouchEvent(event);
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}