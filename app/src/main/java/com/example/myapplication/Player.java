package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Player {

    private Context context;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private int lives;
    private String name;

    private int posX;
    private int posY;

    public Player(Context context, Bitmap bitmap, int lives, String name, Bitmap bitmap2, Bitmap bitmap3) {
        this.context = context;
        this.bitmap = bitmap;
        this.bitmap2 = bitmap2;
        this.bitmap3 = bitmap3;
        this.lives = lives;
        this.name = name;
        posX = 500;
        posY = 500;
    }

    public void draw(Canvas canvas, int marginleft, int marginup, int unit) {
        Paint paint = new Paint();
        final float testTextSize = 70f;
        paint.setTextSize(testTextSize);
        canvas.drawBitmap(bitmap, marginleft, marginup, paint);
        canvas.drawBitmap(bitmap2, 50, 75, paint);
        canvas.drawBitmap(bitmap3, canvas.getWidth() - 250, 55, paint);
        drawLives(canvas, paint);
        drawName(canvas, paint);
        drawCoins(canvas, paint);
    }

    private void drawLives(Canvas canvas, Paint paint) {
        String livesText = "" + this.lives;
        canvas.drawText(livesText, 150, 150, paint);
    }

    private void drawName(Canvas canvas, Paint paint) {
        String levelText = this.name;
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(levelText, canvas.getWidth() / 2, 150, paint);
    }

    private void drawCoins(Canvas canvas, Paint paint) {
        String levelText = "0";
        canvas.drawText(levelText, canvas.getWidth() - 100, 150, paint);
    }

    public void update() {
        // An example of update method
        // It will actually take input from the controller
        // to tell it to move left, right, up, or down
//        String command = "left";
//        if (command.equals("left")) {
//            this.posX = this.posX - 20;
//        }
        ;
    }
}
