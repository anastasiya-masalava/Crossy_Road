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

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawColor(Color.GRAY);
        canvas.drawBitmap(bitmap, posX, posY, paint);
        drawLives(canvas);
        drawName(canvas);
    }

    private void drawLives(Canvas canvas) {
        Paint paint = new Paint();
        String livesText = "Lives: " + this.lives;
        canvas.drawText(livesText, 50, 50, paint);
    }

    private void drawName(Canvas canvas) {
        Paint paint = new Paint();
        String levelText = "Name: " + this.name;
        canvas.drawText(levelText, 150, 50, paint);
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
