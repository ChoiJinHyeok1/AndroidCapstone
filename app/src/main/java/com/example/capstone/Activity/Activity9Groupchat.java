package com.example.capstone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.capstone.R;

public class Activity9Groupchat extends AppCompatActivity {
    Button btnOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity9_groupchat);

        btnOut = findViewById(R.id.btn_Out);

        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}