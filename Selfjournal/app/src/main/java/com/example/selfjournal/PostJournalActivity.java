package com.example.selfjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import util.journalApi;

public class PostJournalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSave;
    private ProgressBar progressBar;
    private ImageView btnAddPhoto;
    private EditText etTitle, etThoughts;
    private TextView tvCurrUser;

    private String currUsername, currUserId;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currentUser;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storageReference;

    private CollectionReference collectionReference = db.collection("Journal");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_journal);

        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.postProgressBar);
        btnSave = findViewById(R.id.btnPostSaveJournal);
        btnAddPhoto = findViewById(R.id.btnPostCamera);
        etTitle = findViewById(R.id.etPostTitle);
        etThoughts = findViewById(R.id.etPostThoughts);
        tvCurrUser = findViewById(R.id.tvPostUsername);

        btnSave.setOnClickListener(this);
        btnAddPhoto.setOnClickListener(this);

        if(journalApi.getInstance() != null){
            currUsername = journalApi.getInstance().getUsername();
            currUserId = journalApi.getInstance().getUserId();

            tvCurrUser.setText(currUsername);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPostSaveJournal:
                break;

            case R.id.btnPostCamera:
                break;
        }
    }
}