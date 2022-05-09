package com.example.capstone; // completed

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity2Login extends AppCompatActivity {
    private EditText edtMail, edtPwd;
    private Button btnJoin, btnLogin;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_login);
        edtMail = findViewById(R.id.edtMail);
        edtPwd = findViewById(R.id.edtPwd);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);
        firebaseAuth = firebaseAuth.getInstance();
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2Login.this, Activity3SignUp.class);
                startActivity(intent);
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
                            Intent mIntent = new Intent(Activity2Login.this, Activity4Certified.class);
                            startActivity(mIntent);
                        } else {Toast.makeText(Activity2Login.this, "로그인을 할 수 없습니다.", Toast.LENGTH_SHORT).show();}
                    }
                });
            }
        });
    }
}