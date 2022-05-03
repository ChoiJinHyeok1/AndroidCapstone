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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Activity3SignUp extends AppCompatActivity {

    private EditText edtSignUpEmail;
    private EditText edtSignUpPwd;
    private EditText edtSignAge;
    private EditText edtSignName;
    private Button btnSign;

    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3_signup);

        edtSignUpEmail = (EditText) findViewById(R.id.edt_SignUp_Email);
        edtSignUpPwd = (EditText) findViewById(R.id.edt_SignUp_Pwd);
        edtSignAge=(EditText)findViewById(R.id.edt_SignUp_Age);
        edtSignName=(EditText)findViewById(R.id.edt_SignUp_Name);
        btnSign = (Button) findViewById(R.id.btn_Sign);

        firebaseAuth = FirebaseAuth.getInstance();

        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtSignUpEmail.getText().toString().trim();
                final String pwd = edtSignUpPwd.getText().toString().trim();
                final String age = edtSignAge.getText().toString().trim();
                final String name = edtSignName.getText().toString().trim();
                //공백인 부분을 제거하고 보여주는 trim();

                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(Activity3SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(Activity3SignUp.this, Activity2Login.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Activity3SignUp.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        });
            }
        });

    }
}