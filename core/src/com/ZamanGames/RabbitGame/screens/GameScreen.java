package com.ZamanGames.RabbitGame.screens;

import com.ZamanGames.RabbitGame.gameworld.GameRender;
import com.ZamanGames.RabbitGame.gameworld.GameWorld;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.ZamanGames.RabbitGame.rhelpers.InputHandler;

/**
 * Created by Ayman on 6/6/2015.
 */

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRender renderer;
    public float gameWidth;
    public float gameHeight;
    public float runTime;

    public GameScreen() {

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();
        float gameWidth = screenWidth;
        float gameHeight = screenHeight;

        int midPointY = (int) (gameHeight / 2);
        int groundY = (int) (gameHeight * 8 / 10) + 15;

        world = new GameWorld((int) gameWidth, (int) gameHeight, midPointY, groundY);
        renderer = new GameRender(world, (int) gameHeight, (int) gameWidth, groundY);

        Gdx.input.setInputProcessor(new InputHandler(world.getRabbit()));

        runTime = 0;

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);

        runTime += delta;
        renderer.render(runTime);

    }

    @Override
    public void resize(int width, int height) {
        //DO THIS SHIT SOON!
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
