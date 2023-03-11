package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.HashSet;
import java.util.Set;


public class Player {
    private Context context;
    private Bitmap bitmap;
    private Bitmap bitmap2;
    private Bitmap bitmap3;

    private Bitmap bitmap4;

    private Bitmap bitmap5;

    private Bitmap bitmap6;

    private Bitmap bitmap7;
    private int lives;
    private String name;

    private int posX;
    private int posY;
    private int score = 0;


    private static Set<Object> positions = new HashSet<>(); // hashset with posY values

    public Player(Context context, Bitmap bitmap, int lives, String name, Bitmap[] bitmaps) {
        this.context = context;
        this.bitmap = bitmap;
        this.bitmap2 = bitmaps[0];
        this.bitmap3 = bitmaps[1];
        this.bitmap4 = bitmaps[2];
        this.bitmap5 = bitmaps[3];
        this.bitmap6 = bitmaps[4];
        this.bitmap7 = bitmaps[5];
        this.lives = lives;
        this.name = name;
        posX = 500;
        posY = 500;
    }

    public int getLives() {
        return lives;
    }

    public String getName() {
        return name;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void loseLife() {
        this.lives = this.lives - 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void draw(Canvas canvas, int marginleft, int marginup, int unit) {
        Paint paint = new Paint();
        final float testTextSize = 70f;
        paint.setColor(Color.WHITE);
        paint.setTextSize(testTextSize);
        int changeX = GamePage.getChangeX();
        int changeY = GamePage.getChangeY();
        // remember previous position
        int prev_x = this.posX;
        int prev_y = this.posY;
        if (changeX == 0 && changeY == 0) {
            this.posY = marginup;
            this.posX = marginleft;
        }
        int newX = marginleft + changeX * 49;
        int newY = marginup + changeY * 49;

        if (newX >= 0 && newX < 950 && newY > 200 && newY < 1270) {
            canvas.drawBitmap(bitmap, newX, newY, paint);
            this.posX = newX;
            this.posY = newY;
        } else {
            if (newX < 0) {
                GamePage.setChangeX(changeX + 1);
            }
            if (newX >= 950) {
                GamePage.setChangeX(changeX - 1);
            }
            if (newY <= 200) {
                GamePage.getChangeY(changeY + 1);
            }
            if (newY >= 1270) {
                GamePage.getChangeY(changeY - 1);
            }
            canvas.drawBitmap(bitmap, this.posX, this.posY, paint);
        }
        // check if we moved up and were on the same height before
        if (prev_x == this.posX && prev_y > this.posY) {
            if (!positions.contains(posY)) {
                // if tile is safe or goal -> add one
                // if tile is river -> add 2
                // if tile is road -> add 3
                update_score();
                positions.add(posY);
            }
        }


        canvas.drawBitmap(bitmap2, 50, 75, paint);
        canvas.drawBitmap(bitmap3, canvas.getWidth() - 250, 55, paint);
        canvas.drawBitmap(bitmap4, canvas.getWidth() - 250, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap5, canvas.getWidth() - 450, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap6, canvas.getWidth() - 650, canvas.getHeight() - 230, paint);
        canvas.drawBitmap(bitmap7, canvas.getWidth() - 850, canvas.getHeight() - 230, paint);
        drawLives(canvas, paint);
        drawName(canvas, paint);
        drawCoins(canvas, paint);
    }

    private void update_score() {
        if (this.posY+bitmap.getHeight() >= Game.getEnd_start_tile()) {
            score++; // start tiles
        } else if (this.posY+bitmap.getHeight() >= Game.getEnd_road_tile()) {
            score += 3; // road tiles
        } else if (this.posY+bitmap.getHeight() >= Game.getEnd_safe_tile()) {
            score++; // safe tiles
        } else if (this.posY+bitmap.getHeight() >= Game.getEnd_river_tile()) {
            score += 2; // river tiles
        } else {
            score++; // goal tiles
        }
    }

    private void drawLives(Canvas canvas, Paint paint) {
        String livesText = "" + this.lives;
        canvas.drawText(livesText, 150, 150, paint);
    }

    private void drawName(Canvas canvas, Paint paint) {
        String levelText = this.name;
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(levelText, canvas.getWidth() / 2, 150, paint);
    }

    private void drawCoins(Canvas canvas, Paint paint) {
        String levelText = Integer.toString(score);
        canvas.drawText(levelText, canvas.getWidth() - 90, 150, paint);
    }


    public void update() {

    }
}
