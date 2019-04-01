package com.example.outlawrampage;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import java.util.Timer;
import java.util.TimerTask;

public class Jebediah {

    private Drawable jeb;

    public Jebediah(Drawable drawable){
        jeb = drawable;
    }

    public void draw (Canvas canvas){
        jeb.setBounds(16,181,16,171);
        jeb.draw(canvas);
    }

    public void right(Canvas canvas){
        /*canvas.drawPicture(R.drawable.cowboywaltz);

        Jebediah.setImageResource(R.drawable.cowboywaltz);
        //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                timer = new Timer(); //need to start a new timer every time you press the button
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Jebediah.setX(Jebediah.getX() + values.getWalkingSpeedRight());
                                if (Jebediah.getDrawable() instanceof AnimationDrawable) {
                                    cowboyWaltz = (AnimationDrawable) Jebediah.getDrawable();
                                }
                                cowboyWaltz.start();
                            }
                        });
                    }
                }, 1, 50);
                return true;

            case MotionEvent.ACTION_UP:
                cowboyWaltz.stop();
                Jebediah.setImageResource(R.drawable.cowboy4_walkwithoutgun_0);
                timer.cancel(); //end the timer when you release the button
                return true;
        }*/

    }

    public void left(){

    }

    public void jump(){

    }

    public void shoot(){

    }
}
