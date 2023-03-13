package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class Sprint3Tests {

    private Game game;
    private Player player;

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


        Bitmap bitmap = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.fox);
        Bitmap bitmap2 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.lifes);
        Bitmap bitmap3 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.coin);

        Bitmap bitmap4 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.down_arrow);
        Bitmap bitmap5 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.up_arrow);
        Bitmap bitmap6 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.right_arrow);
        Bitmap bitmap7 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.left_arrow);

        Bitmap[] bitmaps = new Bitmap[]{bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, bitmap7};
        int[] units = new int[]{unit, onepixel, unitHeight};
        int[] margins = new int[]{marginleft, marginup};
        game = new Game(appContext, "1234", bitmap, 10, bitmaps, units, margins);
    }
    
    private void createNewPlayer() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Bitmap bitmap = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.fox);
        Bitmap bitmap3 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.coin);
        Bitmap bitmap2 = BitmapFactory.decodeResource(appContext.getResources(), R.drawable.lifes);
        Bitmap bitmap4 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.down_arrow);
        Bitmap bitmap5 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.up_arrow);
        Bitmap bitmap6 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.right_arrow);
        Bitmap bitmap7 = BitmapFactory.decodeResource(appContext.getResources(),
                R.drawable.left_arrow);

        Bitmap[] bitmaps = new Bitmap[]{bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, bitmap7};
        player = new Player(appContext, bitmap, 5, "test player", bitmaps);
    }
    
    @Test
    public void noHorizontalScoreUpdate() {
        createNewPlayer();
        int posX = player.getPosX();
        int oldScore = player.getScore();
        player.setPosX(posX + 1);
        player.setPosX(posX + 2);
        player.setPosX(posX + 3);
        player.setPosX(posX + 4);
        player.setPosX(posX + 3);
        player.setPosX(posX + 2);
        int newScore = player.getScore();
        Assert.assertEquals(oldScore, newScore);
    }

    @Test
    public void testObjectNumberLimit() {
        createNewGame();
        for (int i = 0; i < 10000; i++) {
            game.update();
            Assert.assertTrue(game.getMoveables().size() < 10);
        }
    }

    @Test
    public void testObjectVariety() {
        createNewGame();
        boolean hasTruck = false;
        boolean hasCar = false;
        boolean hasRocket = false;
        for (int i = 0; i < 1000; i++) {
            game.update();
            for (Moveable m : game.getMoveables()) {
                if (m instanceof Truck) {
                    hasTruck = true;
                }
                if (m instanceof Car) {
                    hasCar = true;
                }
                if (m instanceof Rocket) {
                    hasRocket = true;
                }
            }
        }
        Assert.assertTrue(hasCar);
        Assert.assertTrue(hasTruck);
        Assert.assertTrue(hasRocket);
    }

    @Test
    public void testObjectDestruction() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        createNewGame();
        Rocket rocket = new Rocket(appContext, 264);
        game.addMoveable(rocket);
        for (int i = 0; i < 1000; i++) {
            game.update();
        }
        Assert.assertFalse(game.getMoveables().contains(rocket));
    }

    @Test
    public void testObjectMoving() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        createNewGame();
        Rocket rocket = new Rocket(appContext, 264);
        int oldX = rocket.getPosX();
        game.addMoveable(rocket);
        game.update();
        Assert.assertEquals(rocket.getPosX(), oldX + rocket.getSpeed());
    }

    @Test
    public void testAddMovable() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        createNewGame();
        Moveable m = new Rocket(appContext, 0);
        game.addMoveable(m);
        ArrayList<Moveable> moveables = game.getMoveables();
        Assert.assertTrue(moveables.contains(m));
    }

    @Test
    public void testRemoveMovable() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        createNewGame();
        Moveable m = new Rocket(appContext, 0);
        game.addMoveable(m);
        ArrayList<Moveable> moveables = game.getMoveables();
        Assert.assertTrue(moveables.contains(m));
        game.removeMoveable(m);
        Assert.assertFalse(moveables.contains(m));
    }

    @Test
    public void testCoordinateCalculation() {
        createNewGame();
        Assert.assertEquals(game.getRowNCoordinateY(7), 264);
        Assert.assertEquals(game.getRowNCoordinateY(20), 290);
    }
}