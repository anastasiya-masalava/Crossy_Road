package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;

//    private final Mapp map;
    public Game(Context context, String player_name, Bitmap inBitmap, int lives) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);
//        // Get the drawable resource ID
//        int resId = R.drawable.frog_1; //я правилно понимаю нам потом это нужно будет изменить??
//
//        // Convert the drawable resource to a Bitmap
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);

        this.player = new Player(getContext(), inBitmap, lives, player_name);
//        this.map = new Mapp(getContext());

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
//        System.out.println(getWidth() + " " + getHeight());

        int width = getWidth();
        int height = getHeight();
        int onepixel = 3;
        int unit = (width - onepixel*11) / 12;
        int marginleft = (int) ((int) unit * 0.5);
        int marginup = 250;
        System.out.println("unit "+ unit);

        canvas.drawColor(Color.GRAY);


        drawMap(canvas, unit, marginleft, marginup, onepixel);
        player.draw(canvas, marginleft + (unit+onepixel)*5, marginup + (unit+onepixel)*14, unit);

    }

    public void drawMap(Canvas canvas, int unit, int marginleft, int marginup, int onepixel){


//        int drawRIdRiver = R.drawable.river;
////                this.getResources().getIdentifier("bunny", "drawable", this.getPackageName());
//        int drawRIdRoad = R.drawable.road;
//        int drawRIdStart = R.drawable.start;
        Bitmap bitmapRiver = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.river), unit, unit);
        Bitmap bitmapRoad = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.road), unit, unit);
        Bitmap bitmapStart = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.start), unit, unit);
        Bitmap bitmapGrass = getResizedBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.grass), unit, unit);
        Paint paint = new Paint();
        drawLine(canvas, 0, paint, unit, marginleft, marginup, bitmapGrass, onepixel);
        drawLine(canvas, 1, paint, unit, marginleft, marginup, bitmapGrass, onepixel);
        drawLine(canvas, 2, paint, unit, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 3, paint, unit, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 4, paint, unit, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 5, paint, unit, marginleft, marginup, bitmapRiver, onepixel);
        drawLine(canvas, 6, paint, unit, marginleft, marginup, bitmapStart, onepixel);
        drawLine(canvas, 7, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 8, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 9, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 10, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 11, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 12, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 13, paint, unit, marginleft, marginup, bitmapRoad, onepixel);
        drawLine(canvas, 14, paint, unit, marginleft, marginup, bitmapStart, onepixel);
    }

    public void drawLine(Canvas canvas, int row, Paint paint, int unit, int marginleft, int marginup, Bitmap bitmap, int onepixel){
        for (int i = 0; i < 11; i++) {
            canvas.drawBitmap(bitmap, marginleft + i*unit + (i)*onepixel, marginup + row*(unit + onepixel), paint);
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
        bm.recycle();
        return resizedBitmap;
    }
}