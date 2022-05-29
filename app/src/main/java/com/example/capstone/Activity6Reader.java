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
import android.widget.Toast;

import com.example.capstone.Info.LikeInfo;
import com.example.capstone.Info.PostInfo;
import com.example.capstone.adapter.commentRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity6Reader extends AppCompatActivity {
    private static final String TAG= "Activity6Reader";
    private ArrayList<item2> iList2;
    private RecyclerView commentRecyclerView;
    private FirebaseUser user;
    private FirebaseFirestore firebaseFirestore;
    private LikeInfo likeInfo;

    TextView tv_Title, tv_Content, tv_likecnt;
    String postId, likecnt, checkid;
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


        //게시글 내용 읽음
        getRead();

        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        // 댓글어댑터(임시)
        setItemInfo();
        setAdapter();


        // 좋아요 버튼 클릭시
        likebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog01.show(); // 다이얼로그 띄우기
                // 취소
                Button noBtn = dialog01.findViewById(R.id.noBtn);
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog01.dismiss(); // 다이얼로그 닫기
                    }
                });
                // 확인
                dialog01.findViewById(R.id.yesBtn).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //좋아요
                        addlikecollection();
                    }
                });
            }
        });


    }


    //게시글 내용 읽음
    private void getRead() {
        Intent intent = getIntent();
        TextView tv_Title = (TextView) findViewById(R.id.tv_Title);
        String stitle = intent.getExtras().getString("title");
        tv_Title.setText(stitle);

        TextView tv_Content = (TextView) findViewById(R.id.tv_Content);
        String scontents = intent.getExtras().getString("contents");
        tv_Content.setText(scontents);

        TextView tv_likecnt = (TextView) findViewById(R.id.tv_likecnt);
        likecnt = intent.getExtras().getString("likecnt");
        tv_likecnt.setText(likecnt);

        postId = intent.getExtras().getString("postId");
    }


    //좋아요
    private void addlikecollection() {

        firebaseFirestore.collection("posts").document(postId).collection("likes").document(user.getUid()).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot documentSnapshot = task.getResult();
                            //이미 존재하면
                            if (documentSnapshot.exists()) {
                                checkid = documentSnapshot.getData().get("check").toString();
                                if(checkid.equals("ok")){
                                    Toast.makeText(Activity6Reader.this,
                                            "이미 좋아요 한 게시글 입니다." , Toast.LENGTH_SHORT).show();
                                    dialog01.dismiss(); // 다이얼로그 닫기
                                }
                                else{
                                    updatelikecnt();
                                }
                            } else { //존재x
                                final DocumentReference likeReference = likeInfo == null ?
                                        firebaseFirestore.collection("posts").document(postId).collection("likes").document(user.getUid())
                                        : firebaseFirestore.collection("posts").document(postId).collection("likes").document(user.getUid());
                                storeUpload(likeReference, new LikeInfo("ok"));
                                updatelikecnt();
                            }
                        }
                    }
                });

    }

    private void storeUpload(DocumentReference likeReference, LikeInfo likeInfo) {
        likeReference.set(likeInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "successfully!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    //좋아요 +1하고 update하는 함수
    private void updatelikecnt() {
        Ilikecnt = Integer.parseInt(likecnt);
        Ilikecnt = Ilikecnt+1;
        firebaseFirestore.collection("posts").document(postId)
                .update("likecnt", Ilikecnt)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.w(TAG, "Success!");
                        dialog01.dismiss(); // 다이얼로그 닫기
                        tv_likecnt.setText(String.valueOf(Ilikecnt));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
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