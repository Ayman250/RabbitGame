package com.ZamanGames.RabbitGame.gameobjects;

import java.util.Random;

/**
 * Created by Ayman on 6/9/2015.
 */
public class Ground extends Scrollable {

    private int groundHeight;
    private float initY;

    private Random r;

    public Ground(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        initY = y;
        groundHeight = 1;

    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        groundHeight = r.nextInt(3) + 1;
        System.out.println(groundHeight);

    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public boolean rabbitOn(Rabbit rabbit) {
        if (rabbit.getX() >= getX() && rabbit.getX() < getX() + getWidth()) {
            return true;
        }
        return false;
    }

    @Override
    public float getY() {
        //System.out.println(initY * groundHeight);
        return initY/groundHeight;
    }

    //determines number of blocks needed to fill screen
}
