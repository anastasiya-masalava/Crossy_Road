package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class ConfigPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }
    public void startGame(View v){
        String textInput = findViewById(R.id.entered_name).toString();

        startActivity(new Intent(ConfigPage.this, GamePage.class));
    }

}
