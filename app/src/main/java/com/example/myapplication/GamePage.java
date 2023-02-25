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
        setContentView(new Game(this));
        // setContentView(R.layout.activity_game); --temporary change
        simpleGrid = (GridView) findViewById(R.id.game_grid);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), logos);
        simpleGrid.setAdapter(customAdapter);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //  player name from ConfigPage
        String playerName = extras.getString("player_name");
        TextView name = (TextView) findViewById(R.id.player_name);
        name.setText(playerName);

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
        TextView numLives = (TextView) findViewById(R.id.num_lifes);
        numLives.setText(lives);

        // sprite from ConfigPage
        String sprite = extras.getString("sprite");
        ImageView imageView = (ImageView) findViewById(R.id.player_img);
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
        imageView.setImageResource(drawableResourceId);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
        Player player = new Player(getApplicationContext(), bitmap, parseInt(lives), playerName);




    }
}
