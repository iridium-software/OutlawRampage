package com.example.outlawrampage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity implements View.OnTouchListener {

    private ImageButton right;
    private ImageButton left;
    private ImageButton jump;
    private ImageButton shoot;
    private ImageView Jebediah;
    private ImageView bullet;
    private float currentLevel;
    private int jumpCount;
    private AnimationDrawable cowboyWaltz;
    private AnimationDrawable cowboyJump;
    private AnimationDrawable cowboyShoot;
    private Handler handler = new Handler();
    private Timer timer;
    private Values values = new Values();

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));

        /*
        Jebediah = findViewById(R.id.Jebediah);
        Jebediah.setImageResource(R.drawable.cowboy4_walkwithoutgun_0);
        bullet = findViewById(R.id.bullet);
        jumpCount = 0;
        currentLevel = values.getCurrentLevel();  //HOW COULD WE CONTINUALLY UPDATE THIS WHILE APP IS RUNNING

        right = findViewById(R.id.right);
        right.setOnTouchListener(this);


        left = findViewById(R.id.left);
        left.setOnTouchListener(this);


        jump = findViewById(R.id.jump);
        jump.setOnTouchListener(this);

        shoot = findViewById(R.id.shoot);
        shoot.setOnTouchListener(this);
        */
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()) {
            case R.id.right:
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
                }

                return false;

            case R.id.left:
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
                                        Jebediah.setX(Jebediah.getX() + values.getWalkingSpeedLeft());
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
                }

                return false;

            case R.id.jump:

                //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        Jebediah.setImageResource(R.drawable.cowboyjump);
                        if (Jebediah.getY() == currentLevel) {
                            jumpCount = 0;
                        }
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
                                                Jebediah.setY(Jebediah.getY() + values.getJumpUpSpeed());
                                                Jebediah.setX(Jebediah.getX() + values.getJumpOverSpeed());
                                                cowboyJump = (AnimationDrawable) Jebediah.getDrawable();
                                                cowboyJump.start();
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
                                                Jebediah.setY(Jebediah.getY() + values.getJumpDownSpeed());
                                                cowboyJump.stop();
                                                Jebediah.setImageResource(R.drawable.cowboystance);
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

                return false;

            case R.id.shoot:


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
                return false;
            default:
                return false;
        }
    }
}