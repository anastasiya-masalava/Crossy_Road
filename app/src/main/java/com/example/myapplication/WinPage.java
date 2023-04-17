package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WinPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_page);
        String score = String.valueOf(Player.getScore());
        TextView textView = (TextView) findViewById(R.id.score);
        textView.setText(score);
    }
    public void exitApp(View v) {
        this.finishAffinity();
    }
    public void toConfigPage(View v) {
        startActivity(new Intent(WinPage.this, ConfigPage.class));
    }
}
