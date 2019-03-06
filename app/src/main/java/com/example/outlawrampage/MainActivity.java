package com.example.outlawrampage;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener{

    private ImageView runner;
    private Handler handler = new Handler();
    private Timer timer;

//    public ImageView getRunner() {
//        return runner;
//    }
//
//    public Button getRight() {
//        return right;
//    }
//
//    public Button getLeft() {
//        return left;
//    }
//
//    public Button getJump() {
//        return jump;
//    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        runner = findViewById(R.id.runner);

        ImageButton right = findViewById(R.id.right);
        right.setOnTouchListener(this);

        ImageButton left = findViewById(R.id.left);
        left.setOnTouchListener(this);


        ImageButton jump = findViewById(R.id.jump);
        jump.setOnTouchListener(this);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View v, MotionEvent event){
        switch(v.getId()){
            case R.id.right:
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
                                        runner.setX(runner.getX() + 5);
                                    }
                                });
                            }
                        }, 1, 50);
                        return true;
                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                }
                return false;
            case R.id.left:
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
                                        runner.setX(runner.getX() - 5);
                                    }
                                });
                            }
                        }, 1, 50);
                        return true;

                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                    }
                return false;
            case R.id.jump:
                //citation: https://www.youtube.com/watch?v=06rVQ1kUXc4
                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

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
                                            runner.setY(runner.getY() - 2);
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
                                            runner.setY(runner.getY() + 2);
                                        }
                                    }, 1000);
                                }
                            }
                        }, 1, 50);
                        return true;

                    case MotionEvent.ACTION_UP:
                        timer.cancel(); //end the timer when you release the button
                        return true;
                }
                return false;
            default:
                return false;
        }
    }
}

