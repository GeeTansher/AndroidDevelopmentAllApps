package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactInfo extends AppCompatActivity {

    TextView tvChar,tvName,tvNumber;
    ImageView ivcall,ivmail,ivedit,ivdelete;
    EditText etName,etNumber,etMail;
    Button btnsubmit;

    private View pblogin;
    private View lvform;
    private TextView tvload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        tvChar = findViewById(R.id.tvChar);
        tvName = findViewById(R.id.tvName);
        tvNumber = findViewById(R.id.tvNumber);

        ivcall = findViewById(R.id.ivcall);
        ivmail = findViewById(R.id.ivmail);
        ivedit = findViewById(R.id.ivedit);
        ivdelete = findViewById(R.id.ivdelete);

        etName = findViewById(R.id.etName);
        etMail = findViewById(R.id.etMail);
        etNumber = findViewById(R.id.etNumber);

        btnsubmit = findViewById(R.id.btnsubmit);

        pblogin=findViewById(R.id.pbLogin);
        lvform=findViewById(R.id.lvForm);
        tvload =findViewById(R.id.tvLoad);

        etNumber.setVisibility(View.GONE);
        etMail.setVisibility(View.GONE);
        etName.setVisibility(View.GONE);
        btnsubmit.setVisibility(View.GONE);

        final Boolean[] edit = {false};
        final int index = getIntent().getIntExtra("index",0);

        etName.setText(ApplicationClass.contacts.get(index).getName());
        etMail.setText(ApplicationClass.contacts.get(index).getEmail());
        etNumber.setText(ApplicationClass.contacts.get(index).getNumber());

        tvChar.setText(ApplicationClass.contacts.get(index).getName().toUpperCase().charAt(0)+"");
        tvName.setText(ApplicationClass.contacts.get(index).getName());
        tvNumber.setText(ApplicationClass.contacts.get(index).getNumber());

        ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // if false and clicked then change to true and vice versa.
                edit[0] = !edit[0];

                if(edit[0])
                {
                    etNumber.setVisibility(View.VISIBLE);
                    etMail.setVisibility(View.VISIBLE);
                    etName.setVisibility(View.VISIBLE);
                    btnsubmit.setVisibility(View.VISIBLE);
                }
                else
                {
                    etNumber.setVisibility(View.GONE);
                    etMail.setVisibility(View.GONE);
                    etName.setVisibility(View.GONE);
                    btnsubmit.setVisibility(View.GONE);
                }

            }
        });

        ivdelete.setOnClickListener(new View.OnClickListener() {
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