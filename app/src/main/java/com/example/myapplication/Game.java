package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

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
    private static int endRiverTile;
    private static int endSafeTile;
    private static int endRoadTile;
    private static int endStartTile;

    public static int getEndRiverTile() {
        return endRiverTile;
    }

    public static int getEndSafeTile() {
        return endSafeTile;
    }

    public static int getEndRoadTile() {
        return endRoadTile;
    }

    public static int getEndStartTile() {
        return endStartTile;
    }

    //    private final Mapp map;
    public Game(Context context, String playerName, Bitmap inBitmap, int lives,
                Bitmap[] bitmaps, int[] units, int[] margins) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);

        this.onepixel = units[1];
        this.unit = units[0];
        this.unitHeight = units[2];
        this.marginleft = margins[0];
        this.marginup = margins[1];
        this.player = new Player(getContext(), inBitmap, lives, playerName, bitmaps);

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
        player.draw(canvas, (int) (marginleft + (unit + onepixel) * 5 - unit * 0.15),
                (int) (marginup + (unitHeight + onepixel) * 14 + (-1.3 * unit + unitHeight)), unit);

    }

    public void drawMap(Canvas canvas) {

        Bitmap bitmapRiver =
                getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.river),
                        unit, unitHeight);
        Bitmap bitmapRoad =
                getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.road),
                        unit, unitHeight);
        Bitmap bitmapStart =
                getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.start),
                        unit, unitHeight);
        Bitmap bitmapGrass = getResizedBitmap(BitmapFactory.decodeResource(getResources(),
                R.drawable.grass), unit, unitHeight);
        Paint paint = new Paint();
        drawLine(canvas, 0, paint, unitHeight, marginleft, marginup, bitmapGrass);
        drawLine(canvas, 1, paint, unitHeight, marginleft, marginup, bitmapGrass);
        endRiverTile = drawLine(canvas, 2, paint, unitHeight, marginleft, marginup, bitmapRiver);
        drawLine(canvas, 3, paint, unitHeight, marginleft, marginup, bitmapRiver);
        drawLine(canvas, 4, paint, unitHeight, marginleft, marginup, bitmapRiver);
        drawLine(canvas, 5, paint, unitHeight, marginleft, marginup, bitmapRiver);
        endSafeTile = drawLine(canvas, 6, paint, unitHeight, marginleft, marginup, bitmapStart);
        endRoadTile = drawLine(canvas, 7, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 8, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 9, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 10, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 11, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 12, paint, unitHeight, marginleft, marginup, bitmapRoad);
        drawLine(canvas, 13, paint, unitHeight, marginleft, marginup, bitmapRoad);
        endStartTile = drawLine(canvas, 14, paint, unitHeight, marginleft, marginup, bitmapStart);
    }

    public int drawLine(Canvas canvas, int row, Paint paint, int unitHeight,
                        int marginleft, int marginup, Bitmap bitmap) {
        for (int i = 0; i < 11; i++) {
            canvas.drawBitmap(bitmap, marginleft + i * unit + (i) * onepixel,
                    marginup + row * (unitHeight + onepixel), paint);
        }
        return marginup + row * (unitHeight + onepixel); // return hight where we start drawing
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