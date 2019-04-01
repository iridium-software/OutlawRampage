package com.example.outlawrampage;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private MainThread thread;
    private Jebediah OlJeb;
    private Drawable background;
    Context context;
    public GameView (Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
        this.context = context;
        background = context.getResources().getDrawable(R.drawable.west);
    }
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
        OlJeb = new Jebediah(context.getResources().getDrawable(R.drawable.cowboy4_walkwithoutgun_0));
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {

    }

    public void draw(Canvas canvas){
        super.draw(canvas);
        if(canvas != null){
            Rect imageBounds= canvas.getClipBounds();
            background.setBounds(imageBounds);
            background.draw(canvas);
            OlJeb.draw(canvas);
        }
    }
}
