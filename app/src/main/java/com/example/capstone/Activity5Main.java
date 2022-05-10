package com.example.capstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.adapter.mainRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Activity5Main extends AppCompatActivity {
    private static final String TAG = "Activity5Main";
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

            //게시글 내용 값 받아오는 코드
            db.collection("posts")
                    //시간순 출력
                    .orderBy("createdAt", Query.Direction.DESCENDING).get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    ArrayList<PostInfo> postList = new ArrayList<>();
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + "=>" + document.getData());
                                        postList.add(new PostInfo(
                                                document.getData().get("title").toString(),
                                                document.getData().get("contents").toString(),
                                                "익명",
                                                document.getTimestamp("createAt")
                                        ));
                                    }
                                    RecyclerView recyclerView = findViewById(R.id.mainRecyclerView);
                                    recyclerView.setHasFixedSize(true);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(Activity5Main.this));
                                    RecyclerView.Adapter mAdapter = new mainRecyclerAdapter(Activity5Main.this, postList);
                                    recyclerView.setAdapter(mAdapter);

                                } else {
                                    Log.d(TAG, "Error", task.getException());
                                }

                        }
                    });

            TextView tvNotice = (TextView) findViewById(R.id.tv_Notice);
            ImageButton ibtnSearch = (ImageButton) findViewById(R.id.ibtn_Search);
            ImageButton ibtnRanChat = (ImageButton) findViewById(R.id.ibtn_RanChat);
            ImageButton ibtnWrite = (ImageButton) findViewById(R.id.ibtn_Write);


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

        protected void onResume(){
            super.onResume();
        }
};
