package com.ZamanGames.RabbitGame.gameobjects;

/**
 * Created by Ayman on 6/22/2015.
 */
public class Spike extends Scrollable {

    public Spike(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);
    }

    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        position.y = newY;
    }
}
