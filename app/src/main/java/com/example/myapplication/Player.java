package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private Context context;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    private Bitmap bitmap4;

    private Bitmap bitmap5;

    private Bitmap bitmap6;

    private Bitmap bitmap7;
    private int lives;
    private String name;

    private int posX;
    private int posY;

    public Player(Context context, Bitmap bitmap, int lives, String name, Bitmap bitmap2, Bitmap bitmap3,
                  Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Bitmap bitmap7) {
        this.context = context;
        this.bitmap = bitmap;
        this.bitmap2 = bitmap2;
        this.bitmap3 = bitmap3;
        this.bitmap4 = bitmap4;
        this.bitmap5 = bitmap5;
        this.bitmap6 = bitmap6;
        this.bitmap7 = bitmap7;
        this.lives = lives;
        this.name = name;
        posX = 500;
        posY = 500;
    }

    public void draw(Canvas canvas, int marginleft, int marginup, int unit) {
        Paint paint = new Paint();
        final float testTextSize = 70f;
        paint.setColor(Color.WHITE);
        paint.setTextSize(testTextSize);
        int change_x = GamePage.getChange_x();
        int change_y = GamePage.getChange_y();
        int one_block = Game.getUnit() * Game.getOnepixel();

        int new_x = marginleft + change_x * 47;
        int new_y = marginup + change_y * 47;


        canvas.drawBitmap(bitmap, new_x, new_y, paint);

        canvas.drawBitmap(bitmap2, 50, 75, paint);
        canvas.drawBitmap(bitmap3, canvas.getWidth() - 250, 55, paint);
        canvas.drawBitmap(bitmap4, canvas.getWidth() - 250, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap5, canvas.getWidth() - 450, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap6, canvas.getWidth() - 650, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap7, canvas.getWidth() - 850, canvas.getHeight() - 230, paint);
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
