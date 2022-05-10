package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Activity11Notice extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity11_notice);

        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_Back);
        TextView tvTitle = (TextView) findViewById(R.id.tv_Title);
        TextView tvContent = (TextView) findViewById(R.id.tv_Content);

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity11Notice.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });
    }
}