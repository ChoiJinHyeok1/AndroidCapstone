package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstone.adapter.commentRecyclerAdapter;

import java.util.ArrayList;

public class Activity6Reader extends AppCompatActivity {
    private ArrayList<item2> iList2;
    private RecyclerView commentRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_reader);

        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_Back);
        ImageView likebtn = (ImageView) findViewById(R.id.likebtn);
        iList2 = new ArrayList<>();

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity6Reader.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });

        Intent intent = getIntent();
        TextView tv_Title = (TextView) findViewById(R.id.tv_Title);
        String stitle = intent.getExtras().getString("title");
        tv_Title.setText(stitle);

        TextView tv_Content = (TextView) findViewById(R.id.tv_Content);
        String scontents = intent.getExtras().getString("contents");
        tv_Content.setText(scontents);

        TextView tv_likecnt = (TextView) findViewById(R.id.tv_likecnt);
        Long llikecnt = intent.getLongExtra("likecnt", 0);
        String slikecnt = llikecnt.toString();
        tv_likecnt.setText(slikecnt);

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