package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity6Reader extends AppCompatActivity {
    private ArrayList<item2> iList2;
    private RecyclerView commentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_reader);

        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        iList2 = new ArrayList<>();

        setItemInfo();
        setAdapter();
    }

    //리사이클러뷰
    private void setAdapter() {
        commentRecyclerAdapter adapter = new commentRecyclerAdapter(iList2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        commentRecyclerView.setLayoutManager(layoutManager);
        commentRecyclerView.setItemAnimator(new DefaultItemAnimator());
        commentRecyclerView.setAdapter(adapter);
    }
    private void setItemInfo() {
        iList2.add(new item2("익명","확인용"));
        iList2.add(new item2("익명2","댓글예시2 입니다."));
        iList2.add(new item2("익명3","댓글예시3 입니다."));
        iList2.add(new item2("익명4","댓글예시4 입니다."));
        iList2.add(new item2("익명5","댓글예시5 입니다."));

    }
}