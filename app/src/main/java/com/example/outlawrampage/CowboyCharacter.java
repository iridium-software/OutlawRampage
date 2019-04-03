package com.example.outlawrampage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceView;
import android.view.View;

public class CowboyCharacter extends SurfaceView {
    public CowboyCharacter(Context context) {
        super(context);
    }

    BitmapFactory factory = new BitmapFactory();
    Bitmap player = factory.decodeResource(getResources(), R.drawable.cowboy4_walkwithoutgun_0);
    float x = 50;
    float y = 50;

    public Bitmap getPlayer() {
        return player;
    }

    public void update(float x, float y) {
        this.x = x;
        this.y = y;
    }

}
