package com.example.capstone.Activity; // completed

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.Toast;

import com.example.capstone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity2Login extends AppCompatActivity {
    private EditText edtMail, edtPwd;
    private ImageView imgvTitle, btnJoin, btnLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_login);
        imgvTitle = findViewById(R.id.imgvActv2Title);
        edtMail = findViewById(R.id.edtLoginMail);
        edtPwd = findViewById(R.id.edtLoginPwd);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);
        firebaseAuth = firebaseAuth.getInstance();
        final Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.animationfortext2);
        imgvTitle.startAnimation(animation);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity2Login.this, Activity3SignUp.class);
                startActivity(mIntent);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usrMail = edtMail.getText().toString().trim();
                String usrPwd = edtPwd.getText().toString().trim(); // String형 변수[usrMail], [usrPwd]([edtMail], [edtPwd]에서 getText())로 로그인하는 것
                firebaseAuth.signInWithEmailAndPassword(usrMail, usrPwd).addOnCompleteListener(Activity2Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent mIntent = new Intent(Activity2Login.this, Activity5Main.class);
                            startActivity(mIntent);
                        } else {
                            Toast.makeText(Activity2Login.this, "이메일 또는 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}