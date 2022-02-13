package com.example.contactmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.contactmanager.model.ContactRoom;
import com.example.contactmanager.model.ContactViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_CONTACT_ACTIVITY_REQUEST_CODE = 1;

    //TODO: replace startActivityForResults() with new one...

    private ContactViewModel contactViewModel;
    private ArrayAdapter<String> arrayAdapter;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
         In this i have use Room related classes for data managing
         no the SQLite one so it's there only for code but no use in this app
        */

        arrayList = new ArrayList<>();

        contactViewModel = new ViewModelProvider.AndroidViewModelFactory(MainActivity.this
                .getApplication())
                .create(ContactViewModel.class);

        contactViewModel.getAllContacts().observe(MainActivity.this,
                contactRooms -> {
//                        StringBuilder builder = new StringBuilder();
                    for (ContactRoom contactRoom : contactRooms) {
//                            builder.append("-").append(contactRoom.getName()).append(" ").append(contactRoom.getOccupation()).append("\n");
//                            Log.d("Ma", "onCreate: " + contactRoom.getName());
                        arrayList.clear();
                        arrayList.add(contactRoom.getName());
                    }
                    // creating arrayAdapter
                    arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            arrayList);

                    // add to listview

                });


        FloatingActionButton btnFloatingAddContact = findViewById(R.id.btnFloatingAddContact);
        btnFloatingAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, NewContact.class);
            startActivityForResult(intent, NEW_CONTACT_ACTIVITY_REQUEST_CODE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_CONTACT_ACTIVITY_REQUEST_CODE &&
                resultCode == RESULT_OK) {
            assert data != null;
            ContactRoom contactRoom = new ContactRoom(data.getStringExtra(NewContact.NAME_REPLY),
                    data.getStringExtra(NewContact.OCCUPATION));
            ContactViewModel.insert(contactRoom);
        }
    }
}