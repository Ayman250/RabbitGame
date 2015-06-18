package com.ZamanGames.RabbitGame.gameobjects;

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

    public Rabbit(float x, float y, int width, int height, int groundY) {
        this.height = height;
        this.width = width;
        this.groundY = groundY;

        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 800);

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
        if(!inAir()){
            velocity.y = 0;
        }

    }

    public void onClick() {
        timePressed = .4f;
    }
    public void onRelease() {

        System.out.println(timePressed);
        if (!inAir())
            if(-300*timePressed < - 200) {
                velocity.add(0, -300 * timePressed);
                System.out.println("if");
            }
        else {
                velocity.add(0, - 200);
                System.out.println("else");
            }
        System.out.println("Pressed");
    }

    public boolean inAir() {
        if (position.y < groundY) {
            return true;
        }
        else {
            return false;
        }
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
