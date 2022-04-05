package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class MainActivity3 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView tvNotice = (TextView)findViewById(R.id.tv_Notice);
        ImageButton ibtnSearch = (ImageButton)findViewById(R.id.ibtn_Search);
        ImageButton ibtnRanChat = (ImageButton)findViewById(R.id.ibtn_RanChat);
        ImageButton ibtnWrite = (ImageButton)findViewById(R.id.ibtn_Write);



        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}