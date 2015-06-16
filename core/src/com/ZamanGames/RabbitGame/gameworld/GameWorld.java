package com.ZamanGames.RabbitGame.gameworld;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameobjects.ScrollHandler;

/**
 * Created by Ayman on 6/6/2015.
 */
public class GameWorld {

    private Rabbit rabbit;
    private ScrollHandler scroller;

    private int gameWidth, gameHeight, groundY;

    public GameWorld(int gameWidth, int gameHeight, float midPointY, int groundY) {

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.groundY = groundY;

        scroller = new ScrollHandler(this, this.gameWidth, this.gameHeight, this.groundY);
        rabbit = new Rabbit(gameWidth/2, groundY, gameWidth/3*2, gameWidth/2);


    }

    public void update(float delta) {
        rabbit.update(delta);
        scroller.update(delta);

    }

    public Rabbit getRabbit() {
        return rabbit;
    }

    public ScrollHandler getScroller() {
        return scroller;
    }

    public int getGameWidth () {
        return this.gameWidth;
    }

    public int getGameHeight() {
        return this.gameHeight;

    }

}
