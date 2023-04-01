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
public class Sprint4Tests {

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

        Bitmap[] bitmaps = new Bitmap[]{bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, bitmap7, bitmap8};
        int[] units = new int[]{unit, onepixel, unitHeight};
        int[] margins = new int[]{marginleft, marginup};
        game = new Game(appContext, "1234", bitmap, 10, bitmaps, units, margins);
        game.sync();
    }

    @Test
    public void testCarCollision() {
        boolean didCollide = false;
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 250);
        player.updateScore(posX, posY);
        for (int i = 0; i < 100; i++) {
            game.update();
            if (game.getDidCollide()) {
                didCollide = true;
            }
        }
        Assert.assertTrue(didCollide);
    }

    @Test
    public void testCarCollisionLifeLost() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 250);
        player.updateScore(posX, posY);
        int oldLives = player.getLives() + 1;
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(oldLives, game.getPlayer().getLives() + 1);
    }

    @Test
    public void testCarCollisionScoreLost() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 250);
        int oldScore = player.getScore();
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(player.getScore(), oldScore);
    }

    @Test
    public void testWaterCollision() {
        boolean didCollide = false;
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 400);
        player.updateScore(posX, posY);
        for (int i = 0; i < 100; i++) {
            game.update();
            if (game.getDidCollide()) {
                didCollide = true;
            }
        }
        Assert.assertTrue(didCollide);
    }

    @Test
    public void testWaterCollisionLifeLost() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 400);
        player.updateScore(posX, posY);
        int oldLives = player.getLives() + 1;
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(oldLives, player.getLives() + 1);
    }

    @Test
    public void testWaterCollisionScoreLost() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 400);
        int oldScore = player.getScore();
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(player.getScore(),  oldScore);
    }

    @Test
    public void testVehicleCollisionRespawn() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        int posY = player.getPosY();
        player.setPosY(posY - 400);
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(player.getPosY(), 100);
    }

    @Test
    public void testWaterCollisionRespawn() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        player.setPosY(player.getPosY() - 250);
        player.setPosY(player.getPosY() - 150);
        for (int i = 0; i < 100; i++) {
            game.update();
        }
        Assert.assertEquals(player.getPosY(), 100);
    }

    @Test
    public void testGoalGameOver() {
        createNewGame();
        Player player = game.getPlayer();
        int posX = player.getPosX();
        player.setPosY(player.getPosY() - 600);
        game.update();
        Assert.assertFalse(game.getGameLoop().getIsRunning());
    }

    @Test
    public void testWaterCollisionGameOver() {
        createNewGame();
        Player player = game.getPlayer();
        player.setLives(1);
        int posX = player.getPosX();
        player.setPosY(player.getPosY() - 400);
        game.update();
        Assert.assertFalse(game.getGameLoop().getIsRunning());
    }

    @Test
    public void testCarCollisionGameOver() {
        createNewGame();
        Player player = game.getPlayer();
        player.setLives(1);
        int posX = player.getPosX();
        player.setPosY(player.getPosY() - 250);
        game.update();
        Assert.assertFalse(game.getGameLoop().getIsRunning());
    }

    @Test
    public void testScoreTilesAfterDeath() {
        createNewGame();
        Player player = game.getPlayer();
        player.setLives(1);
        int posX = player.getPosX();
        int oldScore = player.getScore();
        player.setPosY(player.getPosY() - 250);
        game.update();
        player.setPosY(player.getPosY() - 250);
        Assert.assertEquals(player.getScore(), oldScore);
    }
}