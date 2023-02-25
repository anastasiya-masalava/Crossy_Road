package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
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
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(bitmap, posX, posY, paint);
    }

    public void update() {
        ;
    }
}
