package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toConfigPage(View v) {
        startActivity(new Intent(MainActivity.this, ConfigPage.class));
    }

    public void exitApp(View v) {
        this.finishAffinity();
    }
}