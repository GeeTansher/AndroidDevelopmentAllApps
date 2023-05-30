package com.example.selfjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btngetStarted;
    private TextView tvGratefulText;
    String text = "  What are you grateful about today?     ";
    int i=0,j=text.length()-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btngetStarted = findViewById(R.id.btnGetStarted);
        tvGratefulText = findViewById(R.id.tvGratefulText);
        forward(text);

        btngetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,
                        LoginActivity.class));
            }
        });
    }

    private void forward(String str) {
        if(i<str.length()){
            String string=str.substring(0,i);
            tvGratefulText.setText(string);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    i++;
                    forward(str);
                }
            },50);
        }
        else{
            j=text.length()-1;
            backward(text);
        }
    }

    private void backward(String str){
        if(j>=0){
            String string=str.substring(0,j);
            tvGratefulText.setText(string);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    j--;
                    backward(str);
                }
            },30);
        }
        else{
            i=0;
            forward(text);
        }
    }
}