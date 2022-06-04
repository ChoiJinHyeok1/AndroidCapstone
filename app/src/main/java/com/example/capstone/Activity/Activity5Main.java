package com.example.capstone.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.capstone.Info.PostInfo;
import com.example.capstone.R;
import com.example.capstone.adapter.mainRecyclerAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class Activity5Main extends AppCompatActivity {
    private static final String TAG = "Activity5Main";
    RecyclerView recyclerView;
    ArrayList<PostInfo> postList;
    mainRecyclerAdapter mainRecyclerAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity5_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("로딩중...");
        progressDialog.show();

        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity5Main.this));

        db = FirebaseFirestore.getInstance();
        postList = new ArrayList<>();
        mainRecyclerAdapter = new mainRecyclerAdapter(Activity5Main.this, postList);

        recyclerView.setAdapter(mainRecyclerAdapter);

        EventChangeListener();

        TextView tvNotice = (TextView) findViewById(R.id.tv_Notice);
        ImageButton ibtnSearch = (ImageButton) findViewById(R.id.ibtn_Search);
        ImageButton ibtnRanChat = (ImageButton) findViewById(R.id.ibtn_RanChat);
        ImageButton ibtnWrite = (ImageButton) findViewById(R.id.ibtn_Write);
        TextView tvTitle = (TextView) findViewById(R.id.tv_Title);


        //공지사항
        tvNotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity5Main.this, Activity11Notice.class);
                startActivity(mIntent);
            }
        });
        ibtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity5Main.this, Activity12Search.class);
                startActivity(mIntent);
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

    private void EventChangeListener() {
        db.collection("posts").orderBy("createdAt", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            if (progressDialog.isShowing())
                                progressDialog.show();
                            Log.d("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()){
                            if (dc.getType() == DocumentChange.Type.ADDED){
                                postList.add(dc.getDocument().toObject(PostInfo.class));
                            }
                            mainRecyclerAdapter.notifyDataSetChanged();
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
    protected void onResume(){
            super.onResume();
        }
};
