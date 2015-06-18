package com.ZamanGames.RabbitGame.rhelpers;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.badlogic.gdx.InputProcessor;

/**
 * Created by Ayman on 6/6/2015.
 */
public class InputHandler implements InputProcessor {

    private Rabbit myRabbit;

    public InputHandler(Rabbit rabbit) {
        myRabbit = rabbit;
    }

    @Override
    public boolean keyDown(int keycode) {

        myRabbit.onClick();
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        myRabbit.onRelease();
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
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
