package com.ZamanGames.RabbitGame.gameobjects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ayman on 6/6/2015.
 */
public class Rabbit {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float height, width, groundY;

    private float delta, timePressed;

    private boolean isDead;

    private Rectangle hitBox;

    public Rabbit(float x, float y, int width, int height, int groundY) {
        this.height = height;
        this.width = width;
        this.groundY = groundY;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 2000);
        hitBox = new Rectangle(x, y, width, height);

        isDead = false;

    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));
        this.delta = delta;
        timePressed += delta;

        position.add(velocity.cpy().scl(delta));
        if (position.y > this.groundY) {
            position.y = this.groundY;
        }
        //If rabbit is on the ground set velocity in y to 0;
        if (!inAir()) {
            velocity.y = 0;
        }
        hitBox.x = position.x;
        hitBox.y = position.y;

    }

    public void onClick() {
        timePressed = 1f;
    }

    public void onRelease() {
        if (timePressed > 3) {
            timePressed = 3.4f;
        }

        System.out.println(timePressed);
        if (!inAir())
            if (timePressed > 1.2f) {
                velocity.add(0, -700 - 200 * timePressed);
            } else {
                velocity.add(0, -800);
            }
        System.out.println("Pressed");
    }

    public void changeHeight(float newY) {
        groundY = newY;
    }

    public void die() {
        isDead = true;
        velocity.x = 0;
    }

    public void onRestart(int groundY) {
        position.
    }

    public boolean inAir() {
        if (position.y < groundY) {
            //System.out.println("true");
            return true;
        } else {
            //System.out.println("false");
            return false;
        }
    }

    public boolean isDead() {
        return isDead;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

}
