package com.example.outlawrampage;

/**
 * This class is just for storing the various values such as the currentLevel and walking speed.
 */
  class Values {
    private float currentLevel = 347; //will probably change this to a two-dimensional array
    private int walkingSpeedRight = 5;
    private int walkingSpeedLeft = -5;
    private int jumpUpSpeed = -2;
    private int jumpDownSpeed = 2;
    private int jumpOverSpeed = 1;
    private int bulletSpeed = 50;

    float getCurrentLevel() {
        return currentLevel;
    }

    int getWalkingSpeedRight() {
        return walkingSpeedRight;
    }

    int getWalkingSpeedLeft() {
        return walkingSpeedLeft;
    }

    int getJumpUpSpeed() {
        return jumpUpSpeed;
    }

    int getJumpDownSpeed() {
        return jumpDownSpeed;
    }

    int getJumpOverSpeed() {
        return jumpOverSpeed;
    }

    int getBulletSpeed() {
        return bulletSpeed;
    }
}
