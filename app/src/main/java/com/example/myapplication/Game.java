package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameLoop gameLoop;
    private final Player player;
    public Game(Context context) {
        super(context);

        //Gets the surface holder and adds callback to game
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        this.gameLoop = new GameLoop(this, surfaceHolder);
        // Get the drawable resource ID
        int resId = R.drawable.frog_1;

        // Convert the drawable resource to a Bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);

        this.player = new Player(getContext(), bitmap, 5, "Dennis");

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

        player.draw(canvas);
    }

    public void update() {
        player.update();
    }
}