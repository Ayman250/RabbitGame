package com.ZamanGames.RabbitGame.gameobjects;

import java.util.Random;

/**
 * Created by Ayman on 6/9/2015.
 */
public class Ground extends Scrollable {

    private int groundHeight;
    private float initY;

    private boolean hasSpike;

    private Random r;

    public Ground(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        r = new Random();
        initY = y;
        groundHeight = 0;
        hasSpike = false;

    }

    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        groundHeight = r.nextInt(3);
        hasSpike = false;
        //System.out.println(groundHeight);

    }

    public int getGroundHeight() {
        return groundHeight;
    }

    public boolean rabbitOn(Rabbit rabbit) {
        if (rabbit.getX() + (rabbit.getWidth() - 10) >= getX() && rabbit.getX() + width / 2 < getX() + getWidth()) {
            return true;
        }
        return false;
    }

    public boolean isOn(float xPosition) {
        if (xPosition >= getX() && xPosition < getX() + getWidth()) {
            return true;
        }
        return false;

    }

    public boolean isOnRight(Ground ground) {
        if (getX() > ground.getX()) {
            return true;
        }
        return false;
    }


    public boolean hasSpike() {
        return hasSpike;
    }

    public void setSpike (boolean state){
        hasSpike = state;
    }

    public float getY() {
        //System.out.println(initY * groundHeight);
        return initY - height*groundHeight;
    }

}
