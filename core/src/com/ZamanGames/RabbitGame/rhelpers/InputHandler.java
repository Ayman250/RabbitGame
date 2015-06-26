package com.ZamanGames.RabbitGame.rhelpers;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameworld.GameWorld;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Ayman on 6/6/2015.
 */
public class InputHandler implements InputProcessor {

    private Rabbit rabbit;
    private GameWorld world;

    public InputHandler(GameWorld world) {
        this.world= world;
        rabbit = world.getRabbit();
    }

    @Override
    public boolean keyDown(int keycode) {

        if (this.world.isReady()) {
            this.world.start();
        }
        rabbit.onClick();

        if (this.world.isGameOver() || this.world.isHighScore()) {
            //Reset all variables, go to GameState.Ready
            this.world.restart();
        }
        //System.out.println();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        rabbit.onRelease();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        if (this.world.isReady()) {
            this.world.start();
        }
        rabbit.onClick();

        if (this.world.isGameOver()) {
            //Reset all variables, go to GameState.Ready
            this.world.restart();
        }
        //System.out.println();
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        rabbit.onRelease();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
