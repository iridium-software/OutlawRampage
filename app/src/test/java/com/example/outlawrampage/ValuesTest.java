package com.example.outlawrampage;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValuesTest {
    @Test
    public void testCurrentLevel () {
        Values values = new Values();
        float actual = values.getCurrentLevel();

        assertEquals(347, actual, 0.1);
    }

    @Test
    public void testWalkingSpeedRight () {
        Values values = new Values();
        int actual = values.getWalkingSpeedRight();

        assertEquals(5, actual);
    }

    @Test
    public void testWalkingSpeedLeft () {
        Values values = new Values();
        int actual = values.getWalkingSpeedLeft();

        assertEquals(-5, actual);
    }

    @Test
    public void testJumpUpSpeed () {
        Values values = new Values();
        int actual = values.getJumpUpSpeed();

        assertEquals(-2, actual);
    }

    @Test
    public void testJumpDownSpeed () {
        Values values = new Values();
        int actual = values.getJumpDownSpeed();

        assertEquals(2, actual);
    }

    @Test
    public void testJumpOverSpeed () {
        Values values = new Values();
        int actual = values.getJumpOverSpeed();

        assertEquals(1, actual);
    }

    @Test
    public void testBulletSpeed () {
        Values values = new Values();
        int actual = values.getBulletSpeed();

        assertEquals(50, actual);
    }
}