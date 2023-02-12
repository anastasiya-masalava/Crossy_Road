package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class GamePage extends ConfigPage {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //  player name from ConfigPage
        String name_of_player = extras.getString("player_name");
        TextView name = (TextView) findViewById(R.id.player_name);
        name.setText(name_of_player);

        // difficulty from ConfigPage
        String difficulty = extras.getString("difficulty");
        TextView playerDifficulty = (TextView) findViewById(R.id.player_difficult);
        playerDifficulty.setText(difficulty);
        String lives;
        if (difficulty.equals("Easy")) {
            lives = "5";
        } else if (difficulty.equals("Medium")) {
            lives = "4";
        } else {
            lives = "3";
        }
        TextView num_lives = (TextView) findViewById(R.id.num_lifes);
        num_lives.setText(lives);

        // sprite from ConfigPage
        String sprite = extras.getString("sprite");
        ImageView imageView = (ImageView) findViewById(R.id.player_img);
        int drawableResourceId;
        if (sprite.equals("Frog")) {
            drawableResourceId = this.getResources().getIdentifier("frog_1", "drawable", this.getPackageName());
        } else if (sprite.equals("Fox")) {
            drawableResourceId = this.getResources().getIdentifier("fox", "drawable", this.getPackageName());
        } else {
            drawableResourceId = this.getResources().getIdentifier("bunny", "drawable", this.getPackageName());
        }
        imageView.setImageResource(drawableResourceId);

    }
}
