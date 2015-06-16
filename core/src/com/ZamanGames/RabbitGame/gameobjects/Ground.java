package com.ZamanGames.RabbitGame.gameobjects;

/**
 * Created by Ayman on 6/9/2015.
 */
public class Ground extends Scrollable {

    private int numGround;

    public Ground(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);
    }

    //determines number of blocks needed to fill screen
}
