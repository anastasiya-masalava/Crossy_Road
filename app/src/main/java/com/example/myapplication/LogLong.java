package com.example.myapplication;

import android.content.Context;
import android.graphics.BitmapFactory;

public class LogLong extends MovingObject {

    public LogLong(Context context, int posY) {
        super(context, BitmapFactory.decodeResource(context.getResources(),
                R.drawable.log_long), 0, posY, 4);
    }
}
