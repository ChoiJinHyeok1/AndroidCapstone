package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class Activity5Main extends AppCompatActivity {
    private ArrayList<item1> iList1;
    private RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_main);


        TextView tvNotice = (TextView)findViewById(R.id.tv_Notice);
        ImageButton ibtnSearch = (ImageButton)findViewById(R.id.ibtn_Search);
        ImageButton ibtnRanChat = (ImageButton)findViewById(R.id.ibtn_RanChat);
        ImageButton ibtnWrite = (ImageButton)findViewById(R.id.ibtn_Write);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        iList1 = new ArrayList<>();

        setItemInfo();
        setAdapter();


        //공지사항
        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        ibtnRanChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity5Main.this, Activity8Individchat.class);
                startActivity(mIntent);
            }
        });
        ibtnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity5Main.this, Activity7Writer.class);
                startActivity(mIntent);
            }
        });


    }

    //리사이클러뷰
    private void setAdapter() {
        mainRecyclerAdapter adapter = new mainRecyclerAdapter(iList1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mainRecyclerView.setLayoutManager(layoutManager);
        mainRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mainRecyclerView.setAdapter(adapter);
    }
    private void setItemInfo() {
        iList1.add(new item1("제목1", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목2", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목3", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목4", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목5", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목6", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목7", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목8", "5분 전", "게시글 내용 미리보기"));
        iList1.add(new item1("제목9", "5분 전", "게시글 내용 미리보기"));


    }
}