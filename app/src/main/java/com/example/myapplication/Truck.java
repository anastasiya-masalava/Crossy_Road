package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Truck extends MovingObject {

    public Truck(Context context, int posY) {
        super(context, BitmapFactory.decodeResource(context.getResources(),
                R.drawable.car_1),
                context.getApplicationContext().getResources().getDisplayMetrics().widthPixels,
                posY, -2);
    }
}
