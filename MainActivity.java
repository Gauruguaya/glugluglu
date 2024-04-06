package com.example.wmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PeriodicWorkRequest periodicWork = new PeriodicWorkRequest.Builder(Aviso.class, 1, TimeUnit.HOURS).build();

        WorkManager.getInstance().enqueue(periodicWork);
    }
}