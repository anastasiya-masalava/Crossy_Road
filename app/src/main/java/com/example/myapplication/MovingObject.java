package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;


public class MovingObject implements Moveable {
    private Context context;
    private Bitmap objectBitmap;
    private int posX;
    private int posY;
    private int speed;

    public MovingObject(Context context, Bitmap bitmap, int posX, int posY, int speed) {
        this.context = context;
        this.objectBitmap = bitmap;
        this.posX = posX;
        this.posY = posY;
        this.speed = speed;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        canvas.drawBitmap(this.objectBitmap, this.posX, this.posY, paint);
    }

    @Override
    public void update() {
        this.posX = this.posX + this.speed;
    }
}
