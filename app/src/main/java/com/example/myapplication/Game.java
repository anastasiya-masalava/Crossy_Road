package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;

    public static int getOnepixel() {
        return onepixel;
    }

    private static int onepixel;

    public static int getUnit() {
        return unit;
    }

    public static int getUnitHeight() {
        return unitHeight;
    }

    private static int unit;
    private static int unitHeight;
    private int marginleft;
    private int marginup;
//    private final Mapp map;
    public Game(Context context, String player_name, Bitmap inBitmap, int lives, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Bitmap bitmap7, int width, int height, int unit, int onepixel, int unitHeight, int marginleft, int marginup) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);

        this.onepixel = onepixel;
        this.unit = unit;
        this.unitHeight = unitHeight;
        this.marginleft = marginleft;
        this.marginup = marginup;

        this.player = new Player(getContext(), inBitmap, lives, player_name, bitmap2, bitmap3,
                                bitmap4, bitmap5, bitmap6, bitmap7);

        this.setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvas.drawRGB(41, 41, 41);

        drawMap(canvas);
        player.draw(canvas, (int) (marginleft + (unit+onepixel)*5 - unit*0.15), (int) (marginup + (unitHeight+onepixel)*14 + ( - 1.3*unit + unitHeight)), unit);

    }

    public void drawMap(Canvas canvas){

        Bitmap bitmapRiver = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.river), unit, unitHeight);
        Bitmap bitmapRoad = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.road), unit, unitHeight);
        Bitmap bitmapStart = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.start), unit, unitHeight);
        Bitmap bitmapGrass = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.grass), unit, unitHeight);
        Paint paint = new Paint();
        drawLine(canvas, 0, paint, unit, unitHeight, marginleft, marginup, bitmapGrass, onepixel);
        drawLine(canvas, 1, paint, unit, unitHeight, marginleft, marginup, bitmapGrass, onepixel);
        drawLine(canvas, 2, paint, unit, unitHeight, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 3, paint, unit, unitHeight, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 4, paint, unit, unitHeight, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 5, paint, unit, unitHeight, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 6, paint, unit, unitHeight, marginleft, marginup, bitmapStart, onepixel);
        drawLine(canvas, 7, paint, unit, unitHeight, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 8, paint, unit, unitHeight, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 9, paint, unit, unitHeight, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 10, paint, unit,unitHeight,  marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 11, paint, unit,unitHeight,  marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 12, paint, unit,unitHeight,  marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 13, paint, unit,unitHeight,  marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 14, paint, unit,unitHeight,  marginleft, marginup, bitmapStart, onepixel);
    }

    public void drawLine(Canvas canvas, int row, Paint paint, int unit, int unitHeight, int marginleft, int marginup, Bitmap bitmap, int onepixel){
        for (int i = 0; i < 11; i++) {
            canvas.drawBitmap(bitmap, marginleft + i*unit + (i)*onepixel, marginup + row*(unitHeight + onepixel), paint);
        }
    }

    public void update() {
        player.update();
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
        return resizedBitmap;
    }
}