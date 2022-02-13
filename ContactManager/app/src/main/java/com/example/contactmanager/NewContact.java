package com.example.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewContact extends AppCompatActivity {

    public static final String NAME_REPLY = "name_reply";
    public static final String OCCUPATION = "occupation_reply";
    private EditText etName;
    private EditText etOccupation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        Button btnSave = findViewById(R.id.btnSave);
        etName = findViewById(R.id.etName);
        etOccupation = findViewById(R.id.etOccupation);

        btnSave.setOnClickListener(v -> {

            Intent replyIntent = new Intent();
            if (!TextUtils.isEmpty(etName.getText()) &&
                    !TextUtils.isEmpty(etOccupation.getText())) {

                replyIntent.putExtra(NAME_REPLY, etName.getText().toString().trim());
                replyIntent.putExtra(OCCUPATION, etOccupation.getText().toString().trim());
                setResult(RESULT_OK, replyIntent);
            } else {
                setResult(RESULT_CANCELED, replyIntent);
//                Toast.makeText(this, R.string.empty, Toast.LENGTH_SHORT).show();
            }
            finish();
        });
    }
}