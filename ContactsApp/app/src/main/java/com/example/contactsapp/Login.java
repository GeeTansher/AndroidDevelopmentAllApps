package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    private View pblogin;
    private View lvform;
    private TextView tvload;

    EditText etmail, etpassword;
    Button btnlogin,btnregister;
    TextView tvreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pblogin=findViewById(R.id.pblogin);
        lvform=findViewById(R.id.lvform);
        tvload =findViewById(R.id.tvload);

        etmail = findViewById(R.id.etmail);
        etpassword = findViewById(R.id.etpassword);
        btnlogin = findViewById(R.id.btnlogin);
        btnregister = findViewById(R.id.btnregister);
        tvreset = findViewById(R.id.tvreset);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    startActivity(new Intent(Login.this,Register.class));
                }
                catch (Exception e)
                {
                    Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        tvreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            lvform.setVisibility(show ? View.GONE : View.VISIBLE);
            lvform.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    lvform.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            pblogin.setVisibility(show ? View.VISIBLE : View.GONE);
            pblogin.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    pblogin.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });

            tvload.setVisibility(show ? View.VISIBLE : View.GONE);
            tvload.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    tvload.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            pblogin.setVisibility(show ? View.VISIBLE : View.GONE);
            tvload.setVisibility(show ? View.VISIBLE : View.GONE);
            lvform.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

}