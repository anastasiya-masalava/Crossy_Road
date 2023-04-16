package com.example.myapplication;

import static androidx.test.core.app.ApplicationProvider.getApplicationContext;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

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

    public static void setChangeY(int changeY) {
        GamePage.changeY = changeY;
    }

    private static int changeX = 0;
    private static int changeY = 0;

    public static int getScore() {
        return score;
    }

    private static int score = 0;

    public static boolean isIsExit() {
        return isExit;
    }

    private static boolean isExit = false;

    public static void setIsExit(boolean needsToExit) {
        isExit = needsToExit;
    }

    private static int countVert = 0;
    private static int countHoriz = 0;

    public static boolean soundIsOn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isExit = false;
        Player.setScore(0);
        changeX = 0;
        changeY = 0;
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
        Bitmap bitmap8 = BitmapFactory.decodeResource(getResources(), R.drawable.exit_2);
        Bitmap soundOn = BitmapFactory.decodeResource(getResources(), R.drawable.sound_on);
        Bitmap soundOff = BitmapFactory.decodeResource(getResources(), R.drawable.sound_off);
        //size of player will be 1.3 unit x 1.3 unit
        bitmap = getResizedBitmap(bitmap, (int) ((int) unit * 1.3), (int) ((int) unit * 1.3));
        bitmap2 = getResizedBitmap(bitmap2, 100, 100);
        bitmap3 = getResizedBitmap(bitmap3, 150, 150);
        bitmap4 = getResizedBitmap(bitmap4, 100, 100);
        bitmap5 = getResizedBitmap(bitmap5, 100, 100);
        bitmap6 = getResizedBitmap(bitmap6, 100, 100);
        bitmap7 = getResizedBitmap(bitmap7, 100, 100);
        bitmap8 = getResizedBitmap(bitmap8, 120, 120);
        soundOn = getResizedBitmap(soundOn, 100, 100);
        soundOff = getResizedBitmap(soundOff, 100, 100);

        Bitmap[] bitmaps = new Bitmap[]{bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, bitmap7,
            bitmap8, soundOn, soundOff};

        int[] units = new int[]{unit, onepixel, unitHeight};
        int[] margins = new int[]{marginleft, marginup};
        Game newGame = new Game(this, playerName, bitmap, lives, bitmaps, units, margins, this);
        setContentView(newGame);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() != MotionEvent.ACTION_DOWN) {
            return true;
        }

        float x = event.getX();
        float y = event.getY();
        System.out.println(x);
        System.out.println(y);
        int buttonSize = 50;
        int exitSize = 60;
        int btn1x = Player.getCanvasWidth() - 600 + buttonSize;
        int btn2x = Player.getCanvasWidth() - 600 + buttonSize;
        int btn3x = Player.getCanvasWidth() - 500 + buttonSize;
        int btn4x = Player.getCanvasWidth() - 700 + buttonSize;
        int btny = 1660;
        int soundBtn = Player.getCanvasWidth() - 150 + buttonSize;

        int exitY = 80;
        int exitX = Player.getCanvasWidth() / 2 - 60;

        if (x >= btn1x - buttonSize && x <= btn1x + buttonSize
                && y >= btny + 60 - buttonSize && y <= btny + 60 + buttonSize) {
            System.out.println("Button 1 pressed"); //down
            changeY += 1;
        } else if (x >= btn2x - buttonSize && x <= btn2x + buttonSize
                && y >= btny - 60 - buttonSize && y <= btny - 60 + buttonSize) {
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
        } else if (x >= exitX - exitSize && x <= exitX + exitSize
                && y >= 250 && y <= 350) {
            isExit = true;
            System.out.println("Exit pressed");
            if(soundIsOn) { switchMusicState(); }
            moveToGameOverPage();
        }

        if(x >= soundBtn - buttonSize && x <= soundBtn + buttonSize
                && y >= btny - buttonSize && y <= btny + buttonSize){
            System.out.println("sound is pressed");
            switchMusicState();
        }
        int new_score = Player.updateScore(Player.posX, Player.posY);
//        if (new_score>=15){
//            changeToWinPage();
//        }
        return true;
    }

    private void switchMusicState(){
        soundIsOn = !soundIsOn;
        handleBackgroundSound();
    }

    public static void movePlayerToStart() {
        changeX = 0;
        changeY = 0;
    }
    public void changeToWinPage(){
        isExit = true;
        System.out.println("Success");
        Intent i = new Intent(getApplicationContext(), WinPage.class);
        startActivity(i);

    }
    public void moveToGameOverPage() {
        Intent i = new Intent(getApplicationContext(), ExitPage.class);
        startActivity(i);
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
    public void handleBackgroundSound() {
        Intent intent = new Intent(GamePage.this, BackgroundSoundService.class);
        if(soundIsOn){
            startService(intent);
        } else {
            stopService(intent);
        }
    }
}