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
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    ImageView imgvTitle, btnAttach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity4_certified);
        findViewById(R.id.btnAttach).setOnClickListener(onClickListener);
        storage = FirebaseStorage.getInstance();
        imgvTitle = findViewById(R.id.imgvAct4Title);
        btnAttach = (ImageView)findViewById(R.id.btnAttach);
        final Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.animationfortext2);
        imgvTitle.startAnimation(animation);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAttach:
                    loadAlbum();
                    break;
            }
        }
    };
    private void loadAlbum() {
        Intent mIntent = new Intent(Intent.ACTION_PICK);
        mIntent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(mIntent, GALLERY_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE) {
            Intent mIntent = getIntent();
            String usrData = mIntent.getStringExtra("usrId");
            Uri file = data.getData();
            StorageReference storageRef = storage.getReference();
            StorageReference riversRef = storageRef.child("photo/" + usrData + ".png");
            UploadTask uploadTask = riversRef.putFile(file);
            try {
                InputStream in = getContentResolver().openInputStream(data.getData());
                Bitmap img = BitmapFactory.decodeStream(in);
                in.close();
            } catch (Exception e) { e.printStackTrace(); }
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
                            Waitingforapproval.class);
                    startActivity(mIntent);
                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}







