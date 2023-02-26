package com.example.myapplication;

import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class GamePage extends ConfigPage {
    GridView simpleGrid;
    int logos[] = {
            R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass,
            R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass, R.drawable.grass,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river,
            R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river,
            R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river,
            R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river, R.drawable.river,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road, R.drawable.road,
            R.drawable.start, R.drawable.start, R.drawable.start, R.drawable.start, R.drawable.start, R.drawable.start,
            R.drawable.start, R.drawable.start, R.drawable.start, R.drawable.start, R.drawable.start,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String playerName = extras.getString("player_name");
        System.out.println("Player:" + playerName);
        String difficulty = extras.getString("difficulty");
        ;
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

        DisplayMetrics displayMetrics = new DisplayMetrics();

        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        int onepixel = 3;
        int unit = (width - onepixel*11) / 12;
        int unitHeight = (height - onepixel*11)/25;
        int marginleft = (int) ((int) unit * 0.5);
        int marginup = 250;


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.lifes);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        //size of player will be 1.3 unit x 1.3 unit
        bitmap = getResizedBitmap(bitmap, (int) ((int) unit*1.3), (int) ((int) unit*1.3));
        bitmap2 = getResizedBitmap(bitmap2, 100, 100);
        bitmap3 = getResizedBitmap(bitmap3, 150, 150);


//        System.out.println(width + " " + height);

        Game new_game = new Game(this, playerName, bitmap, lives, bitmap2, bitmap3, width, height, unit, onepixel, unitHeight, marginleft, marginup);
        setContentView(new_game);


    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }


}