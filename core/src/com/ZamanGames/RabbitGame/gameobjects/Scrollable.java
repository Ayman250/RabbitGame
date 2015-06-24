package com.ZamanGames.RabbitGame.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ayman on 6/6/2015.
 */
public class Scrollable {
    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    protected boolean isScrolledLeft;
    protected Rectangle hitBox;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;

        hitBox = new Rectangle(x, y, width, height);
    }

    public void update(float delta) {
        position.add(velocity.cpy().scl(delta));

        //If no longer visible FUCKIT
        if (position.x + width <= 0 ) {
            isScrolledLeft
             = true;
        }
        hitBox.x = position.x;
        hitBox.y = position.y;
    }

    public void reset(float newX, float newY) {
        position.x = newX;
        isScrolledLeft = false;
    }

    public void stop() {
        velocity.x = 0;
    }


    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public boolean collides (Rabbit rabbit) {
        if (Intersector.overlaps(rabbit.getHitBox(), hitBox)) {
            return true;
        }
        return false;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
