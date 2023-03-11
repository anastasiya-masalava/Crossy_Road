package com.example.myapplication;

import android.graphics.Canvas;

public interface Moveable {
    void draw(Canvas canvas);
    void update();

    int getPosX();
}
