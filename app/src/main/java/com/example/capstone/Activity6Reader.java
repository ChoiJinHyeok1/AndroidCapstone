package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstone.adapter.commentRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity6Reader extends AppCompatActivity {
    private static final String TAG= "Activity6Reader";
    private ArrayList<item2> iList2;
    private RecyclerView commentRecyclerView;
    TextView tv_Title, tv_Content, tv_likecnt;
    FirebaseFirestore firebaseFirestore;
    String postId, likecnt;
    Integer Ilikecnt;
    Dialog dialog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_reader);

        dialog01 = new Dialog(Activity6Reader.this); // Dialog 초기화
        dialog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog01.setContentView(R.layout.dialog01);

        commentRecyclerView = findViewById(R.id.commentRecyclerView);
        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_Back);
        ImageView likebtn = (ImageView) findViewById(R.id.likebtn);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        tv_Content = (TextView) findViewById(R.id.tv_Content);
        tv_likecnt = (TextView) findViewById(R.id.tv_likecnt);
        iList2 = new ArrayList<>();

        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity6Reader.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });


        Intent intent = getIntent();
        postId = intent.getExtras().getString("postId");

        // 데이터 읽어오는 코드
        // 느려서 그런지 원래 6화면잠깐 뜨는데 이거 수정예정...
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("posts")
                .document(postId)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                tv_Title.setText(document.getData().get("title").toString());
                                tv_Content.setText(document.getData().get("contents").toString());
                                likecnt = String.valueOf(document.getData().get("likecnt"));
                                tv_likecnt.setText(String.valueOf(document.getData().get("likecnt")));

                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

        // 댓글어댑터(임시)
        setItemInfo();
        setAdapter();

        // 좋아요 클릭시
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog01();

            }

            private void showDialog01() {
                dialog01.show(); // 다이얼로그 띄우기

                // 취소 버튼
                Button noBtn = dialog01.findViewById(R.id.noBtn);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog01.dismiss(); // 다이얼로그 닫기
                    }
                });
                // 확인 버튼
                dialog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        updatelikecnt();
                    }

                });
            }

            private void updatelikecnt() {
                Ilikecnt = Integer.parseInt(likecnt);
                Ilikecnt = Ilikecnt+1;
                firebaseFirestore = FirebaseFirestore.getInstance();
                firebaseFirestore.collection("posts").document(postId)
                        .update("likecnt", Ilikecnt)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.w(TAG, "Success!");
                                dialog01.dismiss(); // 다이얼로그 닫기
                                tv_likecnt.setText(String.valueOf(Ilikecnt));

                                //중복 좋아요 안되게 처리하기!
                                //다이얼로그 꾸미기
                                //좋아요 누를때 애니메이션 추가
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });

            }
        });
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