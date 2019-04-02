package com.example.outlawrampage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;

    CowboyCharacter player;
//    Bitmap player;
    BitmapFactory factory = new BitmapFactory();
//    float x;
//    float y;

    public Game(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

//        x = 0;
//        y = 0;

//        player = factory.decodeResource(getResources(), R.drawable.cowboy4_walkwithoutgun_0);
        player = new CowboyCharacter(context);

        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update(Canvas canvas) {
        canvas.drawBitmap(player.getPlayer(), player.x, player.y, null);
//        canvas.drawBitmap(player, x, y, null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawBitmap(player.getPlayer(), player.x, player.y, null);
//        canvas.drawBitmap(player, x, y, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            player.update(event.getX(), event.getY());
//            x = event.getX();
//            y = event.getY();
        }

        return true;
    }


}