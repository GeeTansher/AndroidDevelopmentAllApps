package com.example.imagecropper;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    private ImageView faceDetectionCameraImage;
    ActivityResultLauncher<String> mGetContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        faceDetectionCameraImage = findViewById(R.id.face_detection_camera_image);


        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), result -> {
            Intent intent = new Intent(MainActivity.this,CropperActivity.class);
            intent.putExtra("DATA",result.toString());
            startActivityForResult(intent,101);
        });
        faceDetectionCameraImage.setOnClickListener(view -> mGetContent.launch("image/*"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==-1 && requestCode==101){
            assert data != null;
            String result = data.getStringExtra("RESULT");
            Uri resultUri=null;
            if(result!=null){
                resultUri = Uri.parse(result);
            }
            faceDetectionCameraImage.setImageURI(resultUri);
        }
    }

}