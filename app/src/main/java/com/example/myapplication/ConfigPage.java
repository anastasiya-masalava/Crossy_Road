package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class ConfigPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
    }
    public void startGame(View v){
        TextInputEditText textInputElement = findViewById(R.id.entered_name);
        String enteredName = textInputElement.getText().toString();
        System.out.println(enteredName);
        if(enteredName == null){
            //Write error message
        }
        startActivity(new Intent(ConfigPage.this, GamePage.class));
    }

}
