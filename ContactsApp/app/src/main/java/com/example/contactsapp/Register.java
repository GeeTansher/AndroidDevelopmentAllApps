package com.example.contactsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class Register extends AppCompatActivity {

    private View pblogin;
    private View lvform;
    private TextView tvload;

    EditText etname, etpassword, etmail, etrepassword;
    Button btnregister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        pblogin=findViewById(R.id.pblogin);
        lvform=findViewById(R.id.lvform);
        tvload =findViewById(R.id.tvload);

        etname = findViewById(R.id.etname);
        etmail = findViewById(R.id.etmail);
        etpassword = findViewById(R.id.etpassword);
        etrepassword = findViewById(R.id.etrepassword);
        btnregister = findViewById(R.id.btnregister);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etname.getText().toString().trim().isEmpty() ||
                        etpassword.getText().toString().trim().isEmpty()||
                        etrepassword.getText().toString().trim().isEmpty()||
                        etmail.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Register.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(etpassword.getText().toString().trim().equals(etrepassword.getText().toString().trim()))
                    {
                        String name= etname.getText().toString().trim();
                        String email = etmail.getText().toString().trim();
                        String password = etpassword.getText().toString().trim();

                        BackendlessUser user = new BackendlessUser();
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setProperty("name",name);

                        showProgress(true);

                        Backendless.UserService.register(user, new AsyncCallback<BackendlessUser>() {
                            @Override
                            public void handleResponse(BackendlessUser response) {
                                showProgress(false);
                                // as we coming in register activity form login activity
                                // so when we will finish this we will go back to login activity
                                Toast.makeText(Register.this, "User Successfully Register", Toast.LENGTH_SHORT).show();
                                Register.this.finish();
                            }

                            @Override
                            public void handleFault(BackendlessFault fault) {
                                Toast.makeText(Register.this, "ERROR: "+fault.getMessage(), Toast.LENGTH_SHORT).show();
                                showProgress(false);
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(Register.this, "Please make sure that your password and retype password in same.", Toast.LENGTH_SHORT).show();
                    }
                }
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