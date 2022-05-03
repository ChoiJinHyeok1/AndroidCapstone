package com.example.capstone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Activity4Certified extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_certified);

        ImageButton imageButton = (ImageButton)findViewById(R.id.ibtn_Attach);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(Activity4Certified.this, Activity5Main.class);
                startActivity(mIntent);
            }
        });
    }
}