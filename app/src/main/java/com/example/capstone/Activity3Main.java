package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;


public class Activity3Main extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);


        TextView tvNotice = (TextView)findViewById(R.id.tv_Notice);
        ImageButton ibtnSearch = (ImageButton)findViewById(R.id.ibtn_Search);
        ImageButton ibtnRanChat = (ImageButton)findViewById(R.id.ibtn_RanChat);
        ImageButton ibtnWrite = (ImageButton)findViewById(R.id.ibtn_Write);
        ListView mainListView = (ListView)findViewById(R.id.main_ListView);


        //공지사항
        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ibtnRanChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity3Main.this, Activity6Individchat.class);
                startActivity(mIntent);
            }
        });
        ibtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity3Main.this, Activity5Writer.class);
                startActivity(mIntent);
            }
        });


    }
}