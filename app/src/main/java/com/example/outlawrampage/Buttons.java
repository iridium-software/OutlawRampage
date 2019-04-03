package com.example.outlawrampage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public class Buttons extends SurfaceView {
    public Buttons(Context context) {
        super(context);
    }

    BitmapFactory factory = new BitmapFactory();
    Bitmap left = factory.decodeResource(getResources(), R.drawable.arrow_left);
    Bitmap right = factory.decodeResource(getResources(), R.drawable.arrow_right);
    Bitmap jump = factory.decodeResource(getResources(), R.drawable.button);
    Bitmap shoot = factory.decodeResource(getResources(), R.drawable.button);

    public Bitmap getLeftB() {
        return left;
    }

    public Bitmap getRightB() {
        return right;
    }

    public Bitmap getJump() {
        return jump;
    }

    public Bitmap getShoot() {
        return shoot;
    }
}
