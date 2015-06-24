package com.ZamanGames.RabbitGame.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/**
 * Created by Ayman on 6/9/2015.
 */
public class Ground extends Scrollable {

    private int groundHeight;
    private float initY;

    protected Spike spike;

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

    //Overrides the collides.
    //Collides with ground is true if rabbit hitBox intersects ground hitBox && rabbit is not on top of ground
    @Override
    public boolean collides(Rabbit rabbit) {
        if (Intersector.overlaps(rabbit.getHitBox(), hitBox) && rabbit.getY() - 10 > getY()) {
            return true;
        }
        return false;
    }

    public boolean hasSpike() {
        return hasSpike;
    }



    public Spike getSpike() {
        return spike;
    }

    public void setSpike (boolean state){
        hasSpike = state;
    }

    public void newSpike (Spike newSpike) {
        spike = newSpike;
    }

    public float getY() {
        //System.out.println(initY * groundHeight);
        return initY - height*groundHeight;
    }

}
