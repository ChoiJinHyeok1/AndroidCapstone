package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Activity8Individchat extends AppCompatActivity {
    private ArrayList<item3> iList3;
    Button btnOut, btnSend;
    EditText edtChat;
    FirebaseDatabase fireDB;
    DatabaseReference chatRef;
    ArrayList<MessageItem> msgArray = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity8_individchat);

        this.initializeData();
        //getSupportActionBar().setTitle("개인채팅방");

        RecyclerView recyclerView = findViewById(R.id.ichatrecyclerView);
        btnOut = findViewById(R.id.btn_Out);
        btnSend = findViewById(R.id.btn_Send);
        edtChat = findViewById(R.id.edt_Chat);

        fireDB = FirebaseDatabase.getInstance();
        chatRef = fireDB.getReference("chat");

        LinearLayoutManager manager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(manager); // LayoutManager 등록
        recyclerView.setAdapter(new chatAdapter(iList3));  // Adapter 등록

        chatRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MessageItem msgItem = snapshot.getValue(MessageItem.class);
                msgArray.add(msgItem);

                    iList3.add(new item3(msgArray.get(msgArray.size()-1).toString(), code.item3.RIGHT_CONTENT));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void clickSend(View view) {
        String msg = edtChat.getText().toString();

        MessageItem msgItem = new MessageItem(msg);
        chatRef.push().setValue(msgItem);
        edtChat.setText("");

        //소프트키패드를 안보이도록
        //키패드가 자동으로 켜지는 것을 방지
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
    }

    public void initializeData()
    {
        iList3 = new ArrayList<>();

        iList3.add(new item3("사용자1님이 입장하셨습니다.", code.item3.CENTER_CONTENT));
        iList3.add(new item3("사용자2님이 입장하셨습니다.", code.item3.CENTER_CONTENT));
        iList3.add(new item3("안녕하세요", code.item3.LEFT_CONTENT));
        iList3.add(new item3("안녕하세요", code.item3.RIGHT_CONTENT));

    }
}