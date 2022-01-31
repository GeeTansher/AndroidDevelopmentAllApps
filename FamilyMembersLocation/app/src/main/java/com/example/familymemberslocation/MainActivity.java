package com.example.familymemberslocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;

public class MainActivity extends AppCompatActivity {

    Button btnPapa,btnPari,btnMummy,btnGeetansh,btnFamily;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnFamily=findViewById(R.id.btnFamily);
        btnPapa=findViewById(R.id.btnPapa);
        btnPari=findViewById(R.id.btnPari);
        btnGeetansh=findViewById(R.id.btnGeetansh);
        btnMummy=findViewById(R.id.btmMummy);

        btnFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                            Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else
                {
                    Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("type","Family");
                    startActivity(intent);
                }
            }
        });

        btnPapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                                PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else
                {
                    Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("type","Papa");
                    startActivity(intent);
                }
            }
        });

        btnMummy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                                PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else
                {
                    Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("type","Mummy");
                    startActivity(intent);
                }
            }
        });

        btnPari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                                PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else
                {
                    Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("type","Pari");
                    startActivity(intent);
                }
            }
        });

        btnGeetansh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                                PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[] {Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},0);
                }
                else
                {
                    Intent intent= new Intent(MainActivity.this,MapsActivity.class);
                    intent.putExtra("type","GeeTansh");
                    startActivity(intent);
                }
            }
        });
    }
}