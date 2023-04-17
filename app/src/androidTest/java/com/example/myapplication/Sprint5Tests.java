package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.HashSet;

@RunWith(AndroidJUnit4.class)
public class Sprint5Tests {

    private Game game;

    private void createNewGame() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int onepixel = 3;
        int marginleft = 5;
        int marginup = 250;
        int unit = (width - onepixel * 11 - marginleft * 2) / 11;
        int unitHeight = (height - onepixel * 11) / 25;


        Bitmap bitmap = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.fox);
        Bitmap bitmap2 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.lifes);
        Bitmap bitmap3 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.coin);

        Bitmap bitmap4 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.down_arrow);
        Bitmap bitmap5 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.up_arrow);
        Bitmap bitmap6 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.right_arrow);
        Bitmap bitmap7 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.left_arrow);
        Bitmap bitmap8 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.exit_2);
        Bitmap soundOn = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.sound_on);
        Bitmap soundOff = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.sound_off);

        Bitmap[] bitmaps = new Bitmap[]{bitmap, bitmap2, bitmap3, bitmap4,
            bitmap5, bitmap6, bitmap7, bitmap8, soundOn, soundOff};
        int[] units = new int[]{unit, onepixel, unitHeight};
        int[] margins = new int[]{marginleft, marginup};
        Player.setScore(0);
        Player.getPositions().clear();
        game = new Game(appContext, "1234", 10,
                bitmaps, units, margins, null);
    }

    @Test
    public void testLogMovement() {
        createNewGame();
        boolean movement = false;
        for (int i = 0; i < 10000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (!(m instanceof LogLong || m instanceof LogSmall)) {
                    continue;
                }
                movement = true;
            }
        }
        Assert.assertTrue(movement);
    }

    @Test
    public void testLogMovementMultipleDirections() {
        createNewGame();
        boolean leftBoundary = false;
        boolean rightBoundary = false;
        for (int i = 0; i < 10000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (!(m instanceof LogLong || m instanceof LogSmall)) {
                    continue;
                }
                if (m.getPosX() > 0 && m.getPosX() < 10) {
                    leftBoundary = true;
                } else if (m.getPosX() > 890 && m.getPosX() < 900) {
                    rightBoundary = true;
                }
            }
        }
        Assert.assertTrue(leftBoundary);
        Assert.assertTrue(rightBoundary);
    }

    @Test
    public void testLogDifferentSpeeds() {
        createNewGame();
        HashSet<Integer> speeds = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (!(m instanceof LogLong || m instanceof LogSmall)) {
                    continue;
                }
                speeds.add(m.getSpeed());
            }
        }
        Assert.assertTrue(speeds.size() > 1);
    }

    @Test
    public void testLogVariety() {
        createNewGame();
        HashSet<Integer> speeds = new HashSet<Integer>();
        boolean smallLogPresent = false;
        boolean longLogPresent = false;
        for (int i = 0; i < 10000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (m instanceof LogSmall) {
                    smallLogPresent = true;
                }
                if (m instanceof LogLong) {
                    longLogPresent = true;
                }
            }
        }
        Assert.assertTrue(smallLogPresent);
        Assert.assertTrue(longLogPresent);
    }

    @Test
    public void testLogQuantity() {
        createNewGame();
        int maxLogCnt = 0;
        for (int i = 0; i < 10000; i++) {
            game.update();
            int logCnt = 0;
            for (Moveable m : game.getMoveables()) {
                if (m instanceof LogLong || m instanceof LogSmall) {
                    logCnt++;
                }
            }
            if (logCnt > maxLogCnt) {
                maxLogCnt = logCnt;
            }
        }
        Assert.assertTrue(maxLogCnt > 3);
    }

    @Test
    public void testLogDifferentHeights() {
        createNewGame();
        HashSet<Integer> heights = new HashSet<Integer>();
        for (int i = 0; i < 10000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (!(m instanceof LogLong || m instanceof LogSmall)) {
                    continue;
                }
                heights.add(m.getPosY());
            }
        }
        Assert.assertTrue(heights.size() > 1);
    }

    @Test
    public void testGoalGameOver() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        player.setPosY(player.getPosY() - 1200);
        game.update();
        Assert.assertFalse(game.getGameLoop().getIsRunning());
    }

    @Test
    public void testGoalGameOverScore() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 1200);
        Player.updateScore(posX, posY);
        Assert.assertEquals(Player.getScore(), 12);
    }

    @Test
    public void testGoalGameOverScoreReset() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 1200);
        game.update();
        createNewGame();
        Assert.assertEquals(Player.getScore(), 0);
    }

    @Test
    public void testGoalGameOverLifeReset() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 800);
        game.update();
        player.setPosY(posY - 1200);
        game.update();
        createNewGame();
        player = game.getPlayer();
        Assert.assertEquals(player.getLives(), 10);
    }

    @Test
    public void testGoalGameOverMoveablesReset() {
        createNewGame();
        Player player = game.getPlayer();
        ArrayList<Moveable> prevMovables = game.getMoveables();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 1200);
        game.update();
        createNewGame();
        Assert.assertNotEquals(prevMovables, game.getMoveables());
    }

    @Test
    public void testGoalGameOverScoreTilesReset() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 300);
        Player.updateScore(posX, posY);
        player.setPosY(posY - 1200);
        game.update();
        createNewGame();
        player = game.getPlayer();
        player.setPosY(posY - 300);
        Player.updateScore(posX, posY);
        Assert.assertEquals(Player.getScore(), 1);
    }
}