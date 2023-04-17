package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.util.Range;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.app.Service;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Random;

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

    private ArrayList<Moveable> movingObjects;
    private Context context;

    private int updatesCount;   // Integer to keep track of how many updates there have been
    private int screenWidth;

    private Random rand = new Random();

    private int delay = 3000;

    private static int endRiverTile;
    private static int endSafeTile;
    private static int endRoadTile;
    private static int endStartTile;
    private GamePage gamePage;

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

    public Player getPlayer() {
        return this.player;
    }

    public GameLoop getGameLoop() {
        return this.gameLoop;
    }

    private boolean didCollide;
    private boolean logDidCollide;
    private int logCollisionSpeed;
    private boolean waterDidCollide;



    //    private final Map map;
    public Game(Context context, String playerName, Bitmap inBitmap, int lives,
                Bitmap[] bitmaps, int[] units, int[] margins, GamePage gamePage) {
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
        this.movingObjects = new ArrayList<>();
        this.updatesCount = 0;
        this.context = context;
        this.screenWidth = context.getApplicationContext()
                .getResources().getDisplayMetrics().widthPixels;
        this.gamePage = gamePage;

        // Add initial moving objects
        addMoveable(new Car(context, getRowNCoordinateY(8)));
        addMoveable(new Rocket(context, getRowNCoordinateY(10)));
        addMoveable(new Truck(context, getRowNCoordinateY(12)));

        this.setFocusable(true);

        this.didCollide = false;
        this.logDidCollide = false;
        this.waterDidCollide = false;
    }

    public void sync() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        if (canvas == null) {
            return;
        }
        super.draw(canvas);

        canvas.drawRGB(41, 41, 41);

        drawMap(canvas);

        drawMoveables(canvas);

        player.draw(canvas, (int) (marginleft + (unit + onepixel) * 5 - unit * 0.15),
                (int) (marginup + (unitHeight + onepixel) * 14 + (-1.3 * unit + unitHeight)), unit);

    }

    public ArrayList<Moveable> getMoveables() {
        return this.movingObjects;
    }

    private void drawMoveables(Canvas canvas) {
        for (int i = 0; i < this.movingObjects.size(); i++) {
            Moveable currentMovingObject = this.movingObjects.get(i);
            currentMovingObject.draw(canvas);
        }
    }

    private void updateMoveables() {
        // We will add a new moving object every x updates.

        // A new Car will be added every 280 updates
        if (updatesCount % 280 == 0) {

            Car newCar = new Car(this.context, getRowNCoordinateY(8));
            addMoveable(newCar);
        }
        // A new Truck will be added every 400 updates
        if (updatesCount % 400 == 0) {
            Truck newTruck = new Truck(this.context, getRowNCoordinateY(12));
            addMoveable(newTruck);
        }
        // A new rocket will be added every 230 updates
        if (updatesCount % 230 == 0) {
            Rocket newRocket = new Rocket(this.context, getRowNCoordinateY(10));
            addMoveable(newRocket);
        }
        // 2 new small logs will be added every 100 updates
        if (updatesCount % 150 == 0) {
            LogSmall newLogSmall = new LogSmall(this.context, getRowNCoordinateY(5) - 25);
            LogSmall newLogSmall2 = new LogSmall(this.context, getRowNCoordinateY(3) - 25);
            addMoveable(newLogSmall);
            addMoveable(newLogSmall2);
        }

        // 2 new big logs will be added every 130 updates
        if (updatesCount % 160 == 0) {
            LogLong newLogLong = new LogLong(this.context, getRowNCoordinateY(4) - 25);
            LogLong newLogLong2 = new LogLong(this.context, getRowNCoordinateY(2) - 25);
            addMoveable(newLogLong);
            addMoveable(newLogLong2);
        }
        didCollide = false;
        logDidCollide = false;
        waterDidCollide = false;
        logCollisionSpeed = 0;
        for (int i = 0; i < this.movingObjects.size(); i++) {
            Moveable currentMovingObject = this.movingObjects.get(i);
            currentMovingObject.update();
            int currentPosX = currentMovingObject.getPosX();
            // Check if object is going off the screen. If so, remove the object from the game
            if (currentPosX > (screenWidth + 10) || currentPosX < (-10 - currentMovingObject.getWidth())) {
                removeMoveable(currentMovingObject);
            }

            // check for collisions
            if (collisionDidOccur(player, currentMovingObject)) {
                System.out.println("did collide with " + i);
                didCollide = true;
                break;
            }


            // check if on a log
            if (logCollisionDidOccur(player, currentMovingObject)){
                logDidCollide = true;
                logCollisionSpeed = currentMovingObject.getSpeed();
                break;
            }

            if (reachedGoalTile(player)){
                gamePage.changeToWinPage();
            }
        }
        // check for water collision
        if (waterCollisionDidOccur(player)) {
            System.out.println("did collide with water");
            didCollide = true;
            waterDidCollide = true;
        }


        player.updateLogCollision(logCollisionSpeed, logDidCollide);
        if (logDidCollide && waterDidCollide) {
            System.out.println("Collided with water but is on a log");
        }

    }

    public boolean waterCollisionDidOccur(Player player) {
        if (player.getPosY() != 500 && player.getPosX() != 500) {
            return (player.getPosY() > getRowNCoordinateY(2)
                    && player.getPosY() < getRowNCoordinateY(5) - 40);
        } else {
            return false;
        }
    }

    public boolean reachedGoalTile(Player player) {
        if (player.getPosY() != 500 && player.getPosX() != 500) {
            return (player.getPosY() < getRowNCoordinateY(2) - 100);
        } else {
            return false;
        }
    }

    public boolean collisionDidOccur(Player player, Moveable vehicle) {
        // Skipping if the moving object is a log
        if (vehicle instanceof LogLong || vehicle instanceof LogSmall) {
            return false;
        }
        if (isInRange(player.getPosX(), vehicle.getPosX(), player.getPlayerWidth(),
                vehicle.getWidth(), 15, 15)
                && isInRange(player.getPosY(), vehicle.getPosY(), player.getPlayerHeight(),
                vehicle.getHeight(), 50, 50)) {
            return true;
        }
        return false;
    }

    public boolean logCollisionDidOccur(Player player, Moveable movingObject) {
        // Only check log objects
        if (!(movingObject instanceof LogLong) && !(movingObject instanceof LogSmall)) {
            return false;
        }
        if (isInRange(player.getPosX(), movingObject.getPosX(), player.getPlayerWidth(),
                movingObject.getWidth(), 15, 15)
                && isInRange(player.getPosY(), movingObject.getPosY(), player.getPlayerHeight(),
                movingObject.getHeight(), 50, 50)) {
            return true;
        }
        return false;
    }

    private boolean isInRange(int pos1, int pos2, int range1, int range2, int offset1,
                              int offset2) {
        int lower1 = pos1;
        int lower2 = pos2;
        int upper1 = pos1 + range1 - offset1;
        int upper2 = pos2 + range2 - offset2;
        Range<Integer> myRange1 = new Range(Math.min(lower1, upper1), Math.max(lower1, upper1));
        Range<Integer> myRange2 = new Range(Math.min(lower2, upper2), Math.max(lower2, upper2));

        try {
            Range<Integer> intersectRange = myRange1.intersect(myRange2);
            if (myRange1.contains(intersectRange)) {
                return true;
            }
        } catch (IllegalArgumentException e) {
        }
        return false;
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

    // Method that gives you the Y coordinate for the nth row.
    public int getRowNCoordinateY(int n) {
        return marginup + n * (unitHeight + onepixel);
    }

    public void update() {
        this.updatesCount++;
        player.update();
        updateMoveables();
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

    public void addMoveable(Moveable newMovingObject) {
        this.movingObjects.add(newMovingObject);
    }

    public void removeMoveable(Moveable movingObjectToRemove) {
        this.movingObjects.remove(movingObjectToRemove);
    }

    public boolean getDidCollide() {
        return didCollide;
    }

    public void setDidCollide(boolean didCollide) {
        this.didCollide = didCollide;
    }

    public boolean getLogDidCollide() {
        return logDidCollide;
    }

    public void setLogDidCollide(boolean logDidCollide) {
        this.logDidCollide = logDidCollide;
    }

    public int getLogCollisionSpeed() {
        return logCollisionSpeed;
    }

    public void manageCollision() {
        player.resetCumulativeLogCollisionSpeed();
        if (player.getLives() > 1) {
            this.player.loseLife();
            Player.setScore(0);
        } else {
            moveToGameOverPage();
        }
    }

    public void moveToGameOverPage() {
        Intent i = new Intent(this.context, ExitPage.class);
        this.context.startActivity(i);
        GamePage.setIsExit(true);
        Intent playSound = new Intent(this.context, BackgroundSoundService.class);
        this.context.stopService(playSound);
    }

}
