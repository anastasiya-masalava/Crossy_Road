package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePage extends ConfigPage {
    GridView simpleGrid;
    int logos[] = {
                    R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass,R.drawable.grass,
                    R.drawable.grass,R.drawable.grass,R.drawable.grass,R.drawable.grass,R.drawable.grass,
                    R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river, R.drawable.river,
                    R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,
                    R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,
                    R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,R.drawable.river,
                    R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,R.drawable.road,
                    R.drawable.start,R.drawable.start,R.drawable.start,R.drawable.start,R.drawable.start, R.drawable.start,
                    R.drawable.start,R.drawable.start,R.drawable.start,R.drawable.start,R.drawable.start,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String playerName = extras.getString("player_name");
        System.out.println("Player:" + playerName);
        String difficulty = extras.getString("difficulty");;
        System.out.println("Difficulty:" + difficulty);
        int lives;
        if (difficulty.equals("Easy")) {
            lives = 5;
        } else if (difficulty.equals("Medium")) {
            lives = 4;
        } else {
            lives = 3;
        }

        String sprite = extras.getString("sprite");
        System.out.println("Sprite:" + sprite);
        int drawableResourceId;
        if (sprite.equals("Frog")) {
            drawableResourceId =
                    this.getResources().getIdentifier("frog_1", "drawable", this.getPackageName());
        } else if (sprite.equals("Fox")) {
            drawableResourceId =
                    this.getResources().getIdentifier("fox", "drawable", this.getPackageName());
        } else {
            drawableResourceId =
                    this.getResources().getIdentifier("bunny", "drawable", this.getPackageName());
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
        Game new_game = new Game(this, playerName, bitmap, lives);
        setContentView(new_game);


    }
}