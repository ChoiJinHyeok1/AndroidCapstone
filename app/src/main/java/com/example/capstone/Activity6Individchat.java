package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Activity6Individchat extends AppCompatActivity {
    private ArrayList<item3> iList3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_individchat);

        this.initializeData();

        RecyclerView recyclerView = findViewById(R.id.ichatrecyclerView);

        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new chatAdapter(iList3));  // Adapter 등록
    }

    public void initializeData()
    {
        iList3 = new ArrayList<>();

        iList3.add(new item3("사용자1님이 입장하셨습니다.", code.item3.CENTER_CONTENT));
        iList3.add(new item3("사용자2님이 입장하셨습니다.", code.item3.CENTER_CONTENT));
        iList3.add(new item3("안녕하세요", code.item3.LEFT_CONTENT));
        iList3.add(new item3("안녕하세요", code.item3.RIGHT_CONTENT));

    }
}