package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ExitPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exit_page);
        String score = String.valueOf(Player.getScore());
        String name = String.valueOf(Player.getName());
        TextView textView = (TextView) findViewById(R.id.num_score);
        TextView textView2 = (TextView) findViewById(R.id.player);
        textView.setText(score);
        textView2.setText("Game over, " + name);
    }

    public void exitApp(View v) {
        this.finishAffinity();
    }

    public void toConfigPage(View v) {
        startActivity(new Intent(ExitPage.this, ConfigPage.class));
    }
}
