package com.example.capstone.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.capstone.Info.PostInfo;
import com.example.capstone.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity7Writer extends AppCompatActivity {
    private static final String TAG= "Activity7Writer";
    private RelativeLayout loaderLayout;
    private FirebaseUser user;
    private PostInfo postInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity7_writer);

//로딩창 뜨는거
        loaderLayout = findViewById(R.id.loaderLayout);

        findViewById(R.id.btn_WriteFin).setOnClickListener(onClickListener);
        findViewById(R.id.btn_PhotoAtt).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_WriteFin:
                    storageUpload();
                    Intent mIntent = new Intent(Activity7Writer.this, Activity5Main.class);
                    startActivity(mIntent);
                    break;
            }
        }
    };

    private void storageUpload() {
        final String title = ((EditText) findViewById(R.id.titleEditText)).getText().toString();
        final String contents = ((EditText) findViewById(R.id.contentsEditText)).getText().toString();
        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        final DocumentReference documentReference = postInfo == null ? firebaseFirestore.collection("posts").document() : firebaseFirestore.collection("posts").document();

        if (title.length() > 0 && contents.length() > 0) {
            loaderLayout.setVisibility(View.VISIBLE);
            user = FirebaseAuth.getInstance().getCurrentUser();

            Timestamp createdAt = Timestamp.now();
            storeUpload(documentReference, new PostInfo(title, contents, user.getUid(), createdAt, 0, documentReference.getId()));

        } else {
            startToast("회원정보를 입력해주세요.");
        }
    }

    private void storeUpload(DocumentReference documentReference, PostInfo postInfo) {
        documentReference.set(postInfo)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "successfully written!");
                        loaderLayout.setVisibility(View.GONE);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
//                        loaderLayout.setVisibility(View.GONE);
                    }
                });
    }


    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}