package com.example.mylove;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ListView listview;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = findViewById(R.id.listView);
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        Toast.makeText(MainActivity.this, "Permission given", Toast.LENGTH_SHORT).show();
                        ArrayList<File> mysongs = fetchsongs(Environment.getExternalStorageDirectory());
                        String [] items = new String[mysongs.size()];
                        for(int i=0;i< mysongs.size();i++){
                            items[i]=mysongs.get(i).getName().replace(".mp3","");
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, items);
                        listview.setAdapter(adapter);

                        listview.setOnItemClickListener((parent, view, position, id) -> {
                            Intent intent=new Intent(MainActivity.this,PlaySong.class);
                            String currentsong = listview.getItemAtPosition(position).toString();
                            intent.putExtra("songsList",mysongs);
                            intent.putExtra("currentSong",currentsong);
                            intent.putExtra("position",position);
                            startActivity(intent);
                        });
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                })
                .check();
    }
    public ArrayList<File> fetchsongs(File file){
        ArrayList<File> arrayList = new ArrayList<>();
        File [] songs = file.listFiles();
        if(songs!=null){
            for(File mf:songs){
                if(!mf.isHidden()&&mf.isDirectory()){
                    arrayList.addAll(fetchsongs(mf));
                }
                else{
                    if(mf.getName().endsWith(".mp3")&&!mf.getName().startsWith(".")){
                        arrayList.add(mf);
                    }
                }
            }
        }
        return arrayList;
    }
}