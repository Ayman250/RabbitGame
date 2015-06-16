package com.ZamanGames.RabbitGame.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Ayman on 6/6/2015.
 */
public class Rabbit {

    private Vector2 position;
    private Vector2 velocity;
    private Vector2 acceleration;

    private float height;
    private float width;

    public Rabbit(float x, float y, int width, int height) {
        this.height = height;
        this.width = width;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, 400);

    }

    public void update(float delta) {
        velocity.add(acceleration.cpy().scl(delta));

        //position.add(velocity.cpy().scl(delta));

        //if (position.y > 200) {
          //  position.y = 200;
        //}
    }

    public void onHoldDown() {

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
