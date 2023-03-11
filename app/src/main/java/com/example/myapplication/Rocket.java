package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Rocket extends MovingObject {

    public Rocket(Context context, int posY) {
        super(context, BitmapFactory.decodeResource(context.getResources(),
                R.drawable.road_rocket2), 0, posY, 5);
    }
}
