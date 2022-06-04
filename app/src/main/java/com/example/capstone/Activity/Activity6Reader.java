package com.example.capstone.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.example.capstone.Info.CommentInfo;
import com.example.capstone.Info.LikeInfo;
import com.example.capstone.R;
import com.example.capstone.adapter.commentRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Activity6Reader extends AppCompatActivity {
    private static final String TAG= "Activity6Reader";
    private ArrayList<CommentInfo> commentInfoArrayList;
    private RecyclerView commentRecyclerView;
    private LottieAnimationView likeAnimButton;
    private FirebaseUser user;
    private FirebaseFirestore firebaseFirestore;
    private LikeInfo likeInfo;
    private CommentInfo commentInfo;
    commentRecyclerAdapter commentRecyclerAdapter;

    TextView tv_Title, tv_Content, tv_likecnt;
    EditText commentEditText;
    String postId, likecnt, commentcnt, checkid, commentId;
    Integer Ilikecnt, Icommentcnt;
    Dialog dialog01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity6_reader);

        dialog01 = new Dialog(Activity6Reader.this); // Dialog 초기화
        dialog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dialog01.setContentView(R.layout.dialog01);

        commentEditText = findViewById(R.id.commentEditText);
        ImageButton ibtnBack = (ImageButton) findViewById(R.id.ibtn_Back);
        ImageView commentsendimg = (ImageView) findViewById(R.id.commentsendimg);
        tv_Title= (TextView) findViewById(R.id.tv_Title);
        tv_Content = (TextView) findViewById(R.id.tv_Content);
        tv_likecnt = (TextView) findViewById(R.id.tv_likecnt);

        likeAnimButton = (LottieAnimationView)findViewById(R.id.likeAnimButton);
        likeAnimButton.setProgress(33);

        firebaseFirestore = FirebaseFirestore.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        //게시글 내용 읽음
        getRead();

        //댓글 내용 읽음
        getComment();


        ibtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity6Reader.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });


        // 좋아요 버튼 클릭시
        likeAnimButton.setOnClickListener(new View.OnClickListener() {
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
                        dialog01.dismiss(); // 다이얼로그 닫기
                        likeAnimButton.setAnimation("good.json");
                        likeAnimButton.playAnimation();
                        addlikecollection();
                    }
                });
            }
        });

        //댓글 전송 버튼 클릭시
        commentsendimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String ccontents = commentEditText.getText().toString();
                final DocumentReference commentReference = commentInfo == null ?
                        firebaseFirestore.collection("posts").document(postId).collection("comments").document()
                        : firebaseFirestore.collection("posts").document(postId).collection("comments").document();
                Timestamp ccreatedAt = Timestamp.now();
                storeUpload(commentReference, new CommentInfo(user.getUid(), ccontents, ccreatedAt, 0));
                getComment();
                updatecommentcnt();
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

    //댓글내용 읽음
    private void getComment() {
        firebaseFirestore.collection("posts").document(postId).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot documentSnapshot = task.getResult();
                            if (documentSnapshot.exists()){
                                commentcnt = documentSnapshot.getData().get("commentcnt").toString();
                            }
                        }
                    }
                });

        firebaseFirestore.collection("posts").document(postId).collection("comments")
                .orderBy("ccreatedAt", Query.Direction.ASCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            commentInfoArrayList = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                commentId = documentSnapshot.getId();
                                Timestamp timestamp = (Timestamp) documentSnapshot.getData().get("ccreatedAt");
                                commentInfoArrayList.add(new CommentInfo(
                                        documentSnapshot.getData().get("ccontents").toString(),
                                        timestamp
                                ));
                            }
                            RecyclerView commentRecyclerView = findViewById(R.id.commentRecyclerView);
                            commentRecyclerView.setHasFixedSize(true);
                            commentRecyclerView.setLayoutManager(new LinearLayoutManager(Activity6Reader.this));
                            RecyclerView.Adapter mAdapter = new commentRecyclerAdapter(Activity6Reader.this, commentInfoArrayList);
                            commentRecyclerView.setAdapter(mAdapter);
                            commentRecyclerView.getAdapter().notifyDataSetChanged();
                        }else{
                            Log.d(TAG, "Error", task.getException());
                        }
                    }
                });
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

    private void storeUpload(DocumentReference commentReference, CommentInfo commentInfo) {
        commentReference.set(commentInfo)
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


    private void updatecommentcnt() {
        Icommentcnt = Integer.parseInt(commentcnt);
        Icommentcnt = Icommentcnt+1;
        firebaseFirestore.collection("posts").document(postId)
                .update("commentcnt", Icommentcnt)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.w(TAG, "Success!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }


}