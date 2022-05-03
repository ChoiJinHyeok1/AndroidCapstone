package com.example.capstone;

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

    private Button btnSignUp;
    private Button btnLogin;
    private EditText edtEmail;
    private EditText edtPwd;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2_login);

        btnSignUp = (Button) findViewById(R.id.btn_SignUp);
        btnLogin = (Button) findViewById(R.id.btn_Login);
        edtEmail = (EditText) findViewById(R.id.edt_Email);
        edtPwd = (EditText) findViewById(R.id.edt_Pwd);

        firebaseAuth = firebaseAuth.getInstance();//firebaseAuth의 인스턴스를 가져옴

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString().trim();
                String pwd = edtPwd.getText().toString().trim();
                //String형 변수 email.pwd(edittext에서 받오는 값)으로 로그인하는것
                firebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(Activity2Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {//성공했을때
                                    Intent intent = new Intent(Activity2Login.this, Activity4Certified.class);
                                    startActivity(intent);
                                } else {//실패했을때
                                    Toast.makeText(Activity2Login.this, "로그인 오류", Toast.LENGTH_SHORT).show();
                                }
                            }
                });
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity2Login.this, Activity3SignUp.class);
                startActivity(intent);
            }
        });
    }
}