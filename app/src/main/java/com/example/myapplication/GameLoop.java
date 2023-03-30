package com.example.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import java.util.Arrays;

public class GameLoop extends Thread {
    private Game game;
    private final SurfaceHolder surfaceHolder;

    private boolean isRunning;
    private int averageUPS;
    private int averageFPS;

    private static final double MAX_UPS = 30.0;
    private static final double UPS_PERIOD = 1000 / MAX_UPS;

    public GameLoop(Game game, SurfaceHolder surfaceHolder) {
        this.game = game;
        this.surfaceHolder = surfaceHolder;

        isRunning = false;
    }

    public double getAverageUPS() {
        return averageUPS;
    }

    public double getAverageFPS() {
        return averageUPS;
    }

    public void startLoop() {
        isRunning = true;
        super.start();
    }

    public static double getMaxUPS() {
        return MAX_UPS;
    }

    @Override
    public void run() {
        super.run();
        System.out.println("Entered run() in GameLoop");

        int updatesCounter = 0;
        int framesCounter = 0;

        long startTime;
        long elapsedTime;
        long sleepTime;

        Canvas canvas = null;

        startTime = System.currentTimeMillis();

        // Height: 1648
        // Width: 1080

        while (isRunning && !GamePage.isIsExit()) {
            try {
                canvas = surfaceHolder.lockCanvas();
                synchronized (surfaceHolder) {
                    game.update();
                    updatesCounter += 1;
                    game.draw(canvas);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(Arrays.toString(e.getStackTrace()));
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        framesCounter += 1;
                    } catch (Exception e) {
                        System.err.println(Arrays.toString(e.getStackTrace()));
                    }
                }
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            sleepTime = (long) (updatesCounter * UPS_PERIOD - elapsedTime);
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime);
                } catch (InterruptedException e) {
                    System.err.println(Arrays.toString(e.getStackTrace()));
                }
            }

            while (sleepTime < 0 && updatesCounter < MAX_UPS - 1) {
                game.update();
                updatesCounter += 1;
                elapsedTime = System.currentTimeMillis() - startTime;
                sleepTime = (long) (updatesCounter * UPS_PERIOD - elapsedTime);
            }

            elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= 1000) {
                averageUPS = (int) (updatesCounter / (0.001 * elapsedTime));
                averageFPS = (int) (framesCounter / (0.001 * elapsedTime));
                updatesCounter = 0;
                framesCounter = 0;
                startTime = System.currentTimeMillis();
            }
        }
    }
}