package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Waitingforapproval extends AppCompatActivity {
    Button btnBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waitingforapproval);
        btnBtn = findViewById(R.id.btnBtn);
        btnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Waitingforapproval.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });
    }
}