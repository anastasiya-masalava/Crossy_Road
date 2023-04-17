package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;

public class LogSmall extends MovingObject {

    public LogSmall(Context context, int posY) {
        super(context, BitmapFactory.decodeResource(context.getResources(),
                        R.drawable.log_small),
                context.getApplicationContext().getResources().getDisplayMetrics().widthPixels,
                posY, -2);
    }

}
