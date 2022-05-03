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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.InputStream;

public class Activity4Certified extends AppCompatActivity {
    Button btintent;
    private  final int GALLERY_CODE = 10; //휴대폰 갤러리에 접근하기 위해 선언한 코드 번호
    ImageView photo;
    private FirebaseStorage storage; //파이어베이스 스토리지에 접근하기 위해 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_certified);

        findViewById(R.id.ibtn_Attach).setOnClickListener(onClickListener);
        photo=(ImageView)findViewById(R.id.ibtn_Attach);
        storage=FirebaseStorage.getInstance(); // 스토리지에 접근하기 위한 인스턴스 선언
        btintent = findViewById(R.id.ibtn_intent);

        btintent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent = new Intent(Activity4Certified.this, Activity5Main.class);
                startActivity(mintent);
            }
        });
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ibtn_Attach:
                    loadAlbum();
                    break;
            }
        }
    };

    private void loadAlbum(){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, GALLERY_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            Uri file = data.getData();
            StorageReference storageRef = storage.getReference();
            StorageReference riversRef = storageRef.child("photo/1.png"); // photo 폴더에 1이라는 파일
            UploadTask uploadTask = riversRef.putFile(file);

            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
                photo.setImageBitmap(img);
            } catch (Exception e) {
                e.printStackTrace();
            }

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(Activity4Certified.this, "사진이 정상적으로 업로드 되지 않았습니다." ,Toast.LENGTH_SHORT).show();
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(Activity4Certified.this, "사진이 정상적으로 업로드 되었습니다." ,Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
//▶ onActivityResult() : 갤러리에서 사용자가 선택한 사진을 파이어 스토어에 올라가도록 하는 함수
//
//        ▶ StorageReference riversRef = storageRef.child : ( ) 안에는 이미지가 저장될 경로를 적는다. 본문의 예제 코드인 "photo/1.png" 같은 경우, 스토리지에 photo 폴더에 1.png 이라는 이름으로 사진이 저장되도록 되어있다.
//
//        이때, 경로 안에 있는 폴더가 스토리지에 존재하지 않으면 스토리지에서 자체적으로 폴더를 생성하여 경로에 맞게 저장해준다.
//
//        (이때 파일 이름을 동적으로 설정하지 않고 본문의 코드처럼 정적으로 설정할 경우, 새로운 이미지 파일을 업로드 시 기존에 있던 이미지 파일에 덮어써지는 문제가 발생한다.)
//
//        ▶ try ~ : 선택한 이미지를 비트맵으로 생성하여 처리하는 부분
//
//        ▶ uploadTask.addOnFailureListener : 스토리지에 정상적으로 이미지 파일을 업로드할 수 없을 때 아래 있는 코드 수행 (본문의 코드 같은 경우 Toast를 사용하여 토스 메시지를 띄우게 했다.)
//
//        ▶ addOnSuccessListener : 스토리지에 정상적으로 이미지 파일이 업로드되었을 때 아래 있는 코드 수행 (본문의 코드 같은 경우 Toast를 사용하여 토스 메시지를 띄우게 했다.






