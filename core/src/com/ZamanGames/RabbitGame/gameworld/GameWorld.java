package com.ZamanGames.RabbitGame.gameworld;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameobjects.ScrollHandler;

/**
 * Created by Ayman on 6/6/2015.
 */
public class GameWorld {

    private Rabbit rabbit;
    private ScrollHandler scroller;
    private int rabbitWidth, rabbitHeight;

    private int gameWidth, gameHeight, groundY, score;
    private float scoreCounter;

    public GameWorld(int gameWidth, int gameHeight, float midPointY, int groundY) {

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.groundY = groundY;

        rabbitWidth = this.gameWidth/12;
        rabbitHeight = this.gameHeight/7;
        rabbit = new Rabbit(this.gameWidth/4-rabbitWidth/40*9, this.groundY, rabbitWidth, rabbitHeight,this.groundY);
        scroller = new ScrollHandler(this, this.gameWidth, this.gameHeight, this.groundY);

        score = 0;
        scoreCounter = 0;


    }

    public void update(float delta) {
        rabbit.update(delta);
        scroller.update(delta);
        //adds point every 1/20th of a second
        scoreCounter += delta;
        if (scoreCounter >= 20) {
            scoreCounter -= 20;
            score++;
        }

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

    public int getScore() {
        return score;
    }

}
