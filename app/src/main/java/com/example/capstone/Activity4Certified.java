package com.example.capstone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class Activity4Certified extends AppCompatActivity {
    private final int GALLERY_CODE = 10;
    private FirebaseStorage storage;
    String usrId;
    ImageView btnAttach;
    Button btnIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_certified);
        findViewById(R.id.ibtn_Attach).setOnClickListener(onClickListener);
        storage = FirebaseStorage.getInstance();
        btnAttach = (ImageView)findViewById(R.id.ibtn_Attach);
        btnIntent = findViewById(R.id.ibtn_Intent);
        btnIntent.setOnClickListener(new View.OnClickListener() { // btnIntent 터치 시 다음 화면
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(Activity4Certified.this,
                        Activity5Main.class);
                startActivity(mIntent);
            }
        });
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibtn_Attach:
                    loadAlbum();
                    break;
            }
        }
    }; // 갤러리 소환
    private void loadAlbum() {
        Intent mIntent = new Intent(Intent.ACTION_PICK);
        mIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(mIntent, GALLERY_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            Uri file = data.getData();
            StorageReference storageRef = storage.getReference();
            StorageReference riversRef = storageRef.child("photo/" + usrId + ".png");//수정 요망@@@@@@@@@@@@@@@@@@@@@@@
            UploadTask uploadTask = riversRef.putFile(file);
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                btnAttach.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(Activity4Certified.this,
                            "사진이 정상적으로 업로드 되지 않았습니다." , Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Activity4Certified.this,
                            "사진이 정상적으로 업로드 되었습니다." , Toast.LENGTH_SHORT).show();
                    Intent mIntent = new Intent(Activity4Certified.this,
                            Waitingforapproval.class); // 승인 대기 페이지로 이동
                }
            });
        }
    }
}







