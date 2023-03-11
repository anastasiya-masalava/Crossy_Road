package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

public class GamePage extends ConfigPage {
    public static int getChangeX() {
        return changeX;
    }

    public static int getChangeY() {
        return changeY;
    }

    public static void setChangeX(int changeX) {
        GamePage.changeX = changeX;
    }

    public static void getChangeY(int changeY) {
        GamePage.changeY = changeY;
    }

    private static int changeX = 0;
    private static int changeY = 0;

    public static int getScore() {
        return score;
    }

    private static int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String playerName = extras.getString("player_name");
        System.out.println("Player:" + playerName);
        String difficulty = extras.getString("difficulty");

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
        int marginleft = 5;
        int marginup = 250;
        int unit = (width - onepixel * 11 - marginleft * 2) / 11;
        int unitHeight = (height - onepixel * 11) / 25;
        System.out.println("Unit:" + unit);
        System.out.println("unitHeight:" + unitHeight);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), drawableResourceId);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.lifes);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.coin);

        Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.down_arrow);
        Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(), R.drawable.up_arrow);
        Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(), R.drawable.right_arrow);
        Bitmap bitmap7 = BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow);
        //size of player will be 1.3 unit x 1.3 unit
        bitmap = getResizedBitmap(bitmap, (int) ((int) unit * 1.3), (int) ((int) unit * 1.3));
        bitmap2 = getResizedBitmap(bitmap2, 100, 100);
        bitmap3 = getResizedBitmap(bitmap3, 150, 150);
        bitmap4 = getResizedBitmap(bitmap4, 150, 150);
        bitmap5 = getResizedBitmap(bitmap5, 150, 150);
        bitmap6 = getResizedBitmap(bitmap6, 150, 150);
        bitmap7 = getResizedBitmap(bitmap7, 150, 150);

        Bitmap[] bitmaps = new Bitmap[]{bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, bitmap7};
        int[] units = new int[]{unit, onepixel, unitHeight};
        int[] margins = new int[]{marginleft, marginup};
        Game newGame = new Game(this, playerName, bitmap, lives, bitmaps, units, margins);
        setContentView(newGame);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return true;
        }

        float x = event.getX();
        float y = event.getY();

        int buttonSize = 75;

        int btn1x = 905;
        int btn2x = 705;
        int btn3x = 505;
        int btn4x = 305;
        int btny = 1660;

        if (x >= btn1x - buttonSize && x <= btn1x + buttonSize
                && y >= btny - buttonSize && y <= btny + buttonSize) {
            System.out.println("Button 1 pressed"); //down
            changeY += 1;
        } else if (x >= btn2x - buttonSize && x <= btn2x + buttonSize
                && y >= btny - buttonSize && y <= btny + buttonSize) {
            System.out.println("Button 2 pressed");
            changeY -= 1;
        } else if (x >= btn3x - buttonSize && x <= btn3x + buttonSize
                && y >= btny - buttonSize && y <= btny + buttonSize) {
            System.out.println("Button 3 pressed");
            changeX += 1;
        } else if (x >= btn4x - buttonSize && x <= btn4x + buttonSize
                && y >= btny - buttonSize && y <= btny + buttonSize) {
            System.out.println("Button 4 pressed");
            changeX -= 1;
        }
        return true;
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