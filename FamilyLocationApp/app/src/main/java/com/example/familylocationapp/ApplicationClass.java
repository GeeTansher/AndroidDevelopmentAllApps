package com.example.familylocationapp;

import android.app.Application;

import com.backendless.Backendless;

public class ApplicationClass extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl("https://api.backendless.com");
        Backendless.initApp(getApplicationContext(),"B4E8543A-5193-07E4-FFAD-4EDA6E9A6900","11F00B59-FE1B-4299-8EDF-24AF8DBD14D9");
    }
}
