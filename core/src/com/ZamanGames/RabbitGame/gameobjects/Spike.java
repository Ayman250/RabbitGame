package com.ZamanGames.RabbitGame.gameobjects;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Ayman on 6/22/2015.
 */
public class Spike extends Scrollable {

    private Rectangle hitBox;

    public Spike(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
        hitBox = new Rectangle(x, y, width, height);
    }

    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        position.y = newY;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        hitBox.x = position.x;
        hitBox.y = position.y;

    }

    public Rectangle getHitBox() {
        return hitBox;
    }
}
