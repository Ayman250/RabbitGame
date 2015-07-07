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

    protected Hill hill1, hill2;

    private boolean hasSpike;

    private Random r;

    public Ground(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
        this.height = height;
        hitBox.width = getWidth()/2;
        r = new Random();
        initY = y;
        groundHeight = 0;
        hasSpike = false;

    }

    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        hitBox.y = getY() + height;
        groundHeight = r.nextInt(3);
        hasSpike = false;
        //System.out.println(groundHeight);

    }

    public int getGroundHeight() {
        return groundHeight;
    }



    public boolean rabbitOn(Rabbit rabbit) {
        return (rabbit.getX() + (rabbit.getWidth() - 10) >= getX() && rabbit.getX() + width / 2 < getX() + getWidth());

    }

    public boolean isOn(float xPosition) {
        if (xPosition >= getX() && xPosition < getX() + getWidth()) {
            return true;
        }
        return false;

    }

    @Override
    public void update(float delta) {
        super.update(delta);
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
        return Intersector.overlaps(rabbit.getHitBox(), hitBox) && rabbit.getY() - 10 > getY();
    }

    /*Called when game is reset (different from reset which is called when
    ground passes screen*/
    public void onReset(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
        groundHeight = 0;
    }

    public void firstNewHill(Hill newHill) {
        hill1 = newHill;
    }

    public void secondNewHill(Hill newHill) {
        hill2 = newHill;
    }

    public void newSpike (Spike newSpike) {
        spike = newSpike;
    }

    public float getY() {
        //System.out.println(initY * groundHeight);
        return initY - height*groundHeight;
    }

}
