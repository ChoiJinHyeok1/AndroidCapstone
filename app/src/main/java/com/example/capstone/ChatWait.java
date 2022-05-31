package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatWait extends AppCompatActivity {
    FirebaseDatabase fireDB;
    String[] chatRooms;
    String user1Uid, user2Uid;
    int userCnt = 0;
    int roomNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_wait);

        Intent intent = new Intent(ChatWait.this, Activity8Individchat.class);

        fireDB = FirebaseDatabase.getInstance();

        if(user1Uid.equals(null)) {
            user1Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userCnt++;
        }
        else {
            user2Uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            userCnt++;
        }

        if(userCnt == 2) {
            if(user1Uid != user2Uid) {
                for(int i = 0; i < roomNum; i++) {
                    if(chatRooms[i] != null) {
                        chatRooms[i] = Integer.toString(i);
                        intent.putExtra("chatRoom", chatRooms[i]);

                        fireDB.getInstance().getReference().child("chatrooms").push().setValue(chatRooms[i]);
                    }
                }

                userCnt = 0;
            }
        }
    }
}