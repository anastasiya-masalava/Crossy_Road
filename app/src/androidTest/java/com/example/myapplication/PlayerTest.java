package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PlayerTest {

    private Player player;




    @Test
    public void testGetName() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        String name = player.getName();

        Assert.assertEquals(name, "test player");
    }

    @Test
    public void testSetName() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        String name = player.getName();

        Assert.assertEquals(name, "test player");

        player.setName("new name");

        Assert.assertEquals(player.getName(), "new name");
    }

    @Test
    public void testGetLives() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        int lives = player.getLives();

        Assert.assertEquals(lives, 5);
    }

    @Test
    public void testSetLives() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        int lives = player.getLives();

        Assert.assertEquals(lives, 5);

        player.setLives(4);

        Assert.assertEquals(player.getLives(), 4);
    }

    @Test
    public void testLoseALife() {

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

        player = new Player(appContext, bitmap, 3, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        int lives = player.getLives();

        Assert.assertEquals(lives, 3);

        player.loseLife();
        Assert.assertEquals(player.getLives(), 2);
    }

    @Test
    public void testLoseAllLives() {

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

        player = new Player(appContext, bitmap, 2, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        int lives = player.getLives();

        Assert.assertEquals(lives, 2);

        player.loseLife();
        player.loseLife();

        Assert.assertEquals(player.getLives(), 0);
    }

    @Test
    public void testGetPosX() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosX(), 500);     // Default is 500
    }

    @Test
    public void testGetPosY() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosY(), 500);     // Default is 500
    }

    @Test
    public void testSetPosX() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosX(), 500);     // Default is 500

        player.setPosX(100);

        Assert.assertEquals(player.getPosX(), 100);
    }

    @Test
    public void testSetPosY() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosY(), 500);     // Default is 500

        player.setPosY(100);

        Assert.assertEquals(player.getPosY(), 100);
    }

    @Test
    public void testGetPositionXY() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosX(), 500);     // Default is 500
        Assert.assertEquals(player.getPosY(), 500);     // Default is 500
    }

    @Test
    public void testSetPositionXY() {

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

        player = new Player(appContext, bitmap, 5, "test player", bitmap2, bitmap3,
                bitmap4, bitmap5, bitmap6, bitmap7);

        Assert.assertEquals(player.getPosX(), 500);     // Default is 500
        Assert.assertEquals(player.getPosY(), 500);     // Default is 500

        player.setPosX(250);
        player.setPosY(250);

        Assert.assertEquals(player.getPosX(), 250);
        Assert.assertEquals(player.getPosY(), 250);
    }

}