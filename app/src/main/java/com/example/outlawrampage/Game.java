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
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class Game extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread thread;

    private Drawable background;

    CowboyCharacter player;
    Buttons buttons;
//    Bitmap player;
    BitmapFactory factory = new BitmapFactory();
//    float x;
//    float y;

    private int jumpCount;
    private Handler handler = new Handler();
    private Timer timer;
    private Values values = new Values();
    Rect rightButton, leftButton, jumpButton, shootButton;

    public Game(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new GameThread(getHolder(), this);

//        x = 0;
//        y = 0;

//        player = factory.decodeResource(getResources(), R.drawable.cowboy4_walkwithoutgun_0);
        player = new CowboyCharacter(context);
        buttons = new Buttons(context);

        background = context.getResources().getDrawable(R.drawable.west);

        setFocusable(true);

        background = context.getResources().getDrawable(R.drawable.west);
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
        canvas.drawBitmap(player.getPlayer(), player.x - player.getPlayer().getWidth() / 2,
                player.y - player.getPlayer().getHeight() / 2, null);
//        canvas.drawBitmap(player, x, y, null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Rect imageBounds= canvas.getClipBounds();
        background.setBounds(imageBounds);
        background.draw(canvas);
        canvas.drawBitmap(player.getPlayer(), player.x - player.getPlayer().getWidth() / 2,
                player.y - player.getPlayer().getHeight() / 2, null);
        canvas.drawBitmap(buttons.getLeftB(), 0, getHeight() - 120, null);
        leftButton = new Rect(
                0,
                getHeight() - 120,
                buttons.getLeftB().getWidth(),
                getHeight() - 120 + buttons.getLeftB().getHeight()
        );
        canvas.drawBitmap(buttons.getRightB(), buttons.getLeftB().getWidth() + 5, getHeight() - 120, null);
        rightButton = new Rect(
                buttons.getLeftB().getWidth() + 5,
                getHeight() - 120,
                buttons.getLeftB().getWidth() + 5 + buttons.getRightB().getWidth(),
                getHeight() - 120 + buttons.getRightB().getHeight()
        );
        canvas.drawBitmap(buttons.getJump(), getWidth() - 300 - buttons.getShoot().getWidth(),
                getHeight() - 150, null);
        jumpButton = new Rect(
                getWidth() - 300 - buttons.getShoot().getWidth(),
                getHeight() - 150,
                getWidth() - 300 - buttons.getShoot().getWidth() + buttons.getJump().getWidth(),
                getHeight() - 150 + buttons.getJump().getHeight()
        );
        canvas.drawBitmap(buttons.getShoot(), getWidth() - 300, getHeight() - 150, null);
        shootButton = new Rect (
                getWidth() - 300,
                getHeight() - 150,
                getWidth() - 300 + buttons.getShoot().getWidth(), 
                getHeight() - 150 + buttons.getShoot().getHeight()
        );

//        canvas.drawBitmap(player, x, y, null);
    }

    public boolean onTouchEvent(MotionEvent event){
        if(rightButton.contains((int)event.getX(), (int)event.getY())) {
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
                                    player.update(player.getX() + values.getWalkingSpeedRight(), player.getY());
                                }
                            });
                        }
                    }, 1, 50);
                    return true;

                case MotionEvent.ACTION_UP:
                    timer.cancel(); //end the timer when you release the button
                    return true;
            }
        }
        else if(leftButton.contains((int)event.getX(), (int)event.getY())) {
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
                                    player.update(player.getX() + values.getWalkingSpeedLeft(), player.getY());
                                }
                            });
                        }
                    }, 1, 50);
                    return true;

                case MotionEvent.ACTION_UP:
                    timer.cancel(); //end the timer when you release the button
                    return true;
            }
        }
        else if(jumpButton.contains((int)event.getX(), (int)event.getY())) {
            //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:
                    /*if (player.getY() == Values.getCurrentLevel()) {
                        jumpCount = 0;
                    }*/
                    if (jumpCount == 0) {
                        final long startTime = System.currentTimeMillis();
                        //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                        timer = new Timer(); //need to start a new timer every time you press the button
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                for (int i = 0; i < 10; i++) {
                                    //citation: https://www.youtube.com/watch?v=PGMrMZLNhUk
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            player.update(player.getX() + values.getJumpOverSpeed(), player.getY() + values.getJumpUpSpeed());
                                        }
                                    }, 500);
                                }
                                if (System.currentTimeMillis() >= startTime + 500) {
                                    timer.cancel();
                                }
                                for (int i = 0; i < 10; i++) {
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            player.update(player.getX(), player.getY() + values.getJumpDownSpeed());
                                            jumpCount++;
                                        }
                                    }, 1000);
                                }
                            }
                        }, 1, 50);
                    }
                    return true;

                case MotionEvent.ACTION_UP:
                    if (timer != null) {
                        timer.cancel(); //end the timer when you release the button
                    }
                    return true;
            }
        }
        /*
        else if(shootButton.contains((int)event.getX(), (int)event.getY())) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    Jebediah.setImageResource(R.drawable.cowboyshoot);
                    bullet.setImageResource(R.drawable.bullet);// need a bullet sprite
                    final float JebX = Jebediah.getX();
                    final float JebY = Jebediah.getY();
                    bullet.setX(JebX + 150);
                    bullet.setY(JebY + 75);
                    bullet.setVisibility(View.VISIBLE);

                    //citation: https://www.youtube.com/watch?v=UxbJKNjQWD8
                    timer = new Timer(); //need to start a new timer every time you press the button
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    bullet.setX(bullet.getX() + values.getBulletSpeed());
                                    if (Jebediah.getDrawable() instanceof AnimationDrawable) {
                                        cowboyShoot = (AnimationDrawable) Jebediah.getDrawable();
                                    }
                                    cowboyShoot.start();
                                }
                            }, 250);
                        }
                    }, 1, 50);
            }
        }*/
        return false;
    }


}