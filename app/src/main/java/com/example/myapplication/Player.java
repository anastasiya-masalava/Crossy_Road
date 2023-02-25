package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

public class Player {
    private Context context;
    private Bitmap bitmap;
    private int lives;
    private String name;

    private int posX;
    private int posY;

    public Player(Context context, Bitmap bitmap, int lives, String name) {
        this.context = context;
        this.bitmap = bitmap;
        this.lives = lives;
        this.name = name;
        posX = 500;
        posY = 500;
    }

    public void draw(Canvas canvas, int marginleft, int marginup, int unit) {
        Paint paint = new Paint();
        final float testTextSize = 100f;
        paint.setTextSize(testTextSize);
        canvas.drawBitmap(bitmap, marginleft, marginup-75 + unit/2, paint);
        drawLives(canvas, paint);
        drawName(canvas, paint);
    }

    private void drawLives(Canvas canvas, Paint paint) {
//        Paint paint = new Paint();
        String livesText = "Lives: " + this.lives;
        canvas.drawText(livesText, 50, 150, paint);
    }

    private void drawName(Canvas canvas, Paint paint) {
//        Paint paint = new Paint();
        String levelText = "Name: " + this.name;
        canvas.drawText(levelText, 0, 150, paint);
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
