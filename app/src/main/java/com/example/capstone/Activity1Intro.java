package com.example.capstone;

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

        ImageView imageView1 = (ImageView)findViewById(R.id.imgv_Title);
        ImageView imageView2 = (ImageView)findViewById(R.id.imgv_MainDoor);
        ImageView imageView3 = (ImageView)findViewById(R.id.imgv_TapToEnter);

        final Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.translate);
        imageView1.startAnimation(animation1);
        final Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.test);
        imageView3.startAnimation(animation2);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent = new Intent(Activity1Intro.this,Activity2Certified.class);
                startActivity(mintent);
            }
        });
    }
}