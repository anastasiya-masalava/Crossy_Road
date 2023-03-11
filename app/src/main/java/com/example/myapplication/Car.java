package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;

public class Car extends MovingObject {

    public Car(Context context, int posY) {
        super(context, BitmapFactory.decodeResource(context.getResources(),
                R.drawable.car_3),
                context.getApplicationContext().getResources().getDisplayMetrics().widthPixels,
                posY, -3);
    }
}
