package com.ZamanGames.RabbitGame.gameworld;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameobjects.ScrollHandler;
import com.ZamanGames.RabbitGame.rhelpers.AssetLoader;

/**
 * Created by Ayman on 6/6/2015.
 */
public class GameWorld {

    private Rabbit rabbit;
    private ScrollHandler scroller;
    private int rabbitWidth, rabbitHeight;

    private int gameWidth, gameHeight, groundY, score;
    private float scoreCounter, runTime = 0;

    private boolean scoring;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE;
    }

    public GameWorld(int gameWidth, int gameHeight, float midPointY, int groundY) {
        currentState = GameState.READY;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.groundY = groundY;

        rabbitWidth = 85;
        rabbitHeight = 90;
        rabbit = new Rabbit(this.gameWidth/4-rabbitWidth/40*9, this.groundY, rabbitWidth, rabbitHeight,this.groundY);
        scroller = new ScrollHandler(this, this.gameWidth, this.gameHeight, this.groundY);

        score = 0;
        scoreCounter = 0;

        scoring = true;

    }

    public void update(float delta) {

        runTime += delta;

        switch (currentState) {
            case READY:
            case MENU:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
            default:
                break;
        }
    }

    private void updateReady(float delta) {
        rabbit.updateReady(runTime);
    }


    public void updateRunning(float delta) {
        if (delta > .15f) {
            delta = .15f;
        }

        rabbit.update(delta);
        scroller.update(delta);
        //adds point every 1/20th of a second
        scoreCounter += delta;
        if (scoring) {
            if (scoreCounter >= 1 / 10f) {
                scoreCounter -= 1 / 10f;
                score++;
            }
        }
      
        if (scroller.rabbitCollides()) {
            scroller.stop();
            stopMusic();
            rabbit.die();
            currentState = GameState.GAMEOVER;

            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                currentState = GameState.HIGHSCORE;
            }
        }


    }

    public void ready() {
        currentState = GameState.READY;
    }

    public void start() {
        currentState = GameState.RUNNING;
        playMusic();
    }

    public void restart() {
        currentState = GameState.READY;
        score = 0;
        scoring = true;
        rabbit.onRestart(this.groundY);
        scroller.onRestart();
        currentState = GameState.READY;

    }

    public void playMusic() {
        AssetLoader.bgMusic.play();
    }

    public void pauseMusic() {
        AssetLoader.bgMusic.pause();
    }

    public void stopMusic() {
        AssetLoader.bgMusic.stop();
    }

    public boolean isHighScore() {
        return currentState == GameState.HIGHSCORE;
    }

    public boolean isReady() {
        return currentState == GameState.READY;
    }

    public boolean isGameOver() {
        return currentState == GameState.GAMEOVER;
    }

    public boolean isRunning() {
        return currentState == GameState.RUNNING;
    }

    public boolean isMenu() {
        return currentState == GameState.MENU;
    }

    //Used in case scoring needs to be resumed while game is still running.
    public void startScoring() {
        scoring = true;
    }

    //Used if rabbit dies or game is paused
    public void stopScoring() {
        scoring = false;
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
