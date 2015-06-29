package com.ZamanGames.RabbitGame.rhelpers;

import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameworld.GameWorld;
import com.ZamanGames.RabbitGame.ui.Button;
import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman on 6/6/2015.
 */
public class InputHandler implements InputProcessor {

    private Rabbit rabbit;
    private GameWorld world;

    private List<Button> menuButtons;

    private float scaleFactorX, scaleFactorY;

    private Button playButton;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
        this.world = world;
        rabbit = world.getRabbit();

        int midPointY = world.getGameHeight() / 2;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        menuButtons = new ArrayList<Button>();
        playButton = new Button(world.getGameWidth() / 2 - AssetLoader.playButtonUp.getWidth() / 2, world.getGameHeight() / 2 + 100, 210, -70,
                AssetLoader.playButtonUp, AssetLoader.playButtonDown);
        menuButtons.add(playButton);
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
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        System.out.println(screenX + " " + screenY);

        if (world.isMenu()) {
            playButton.isTouchDown(screenX, screenY);
        } else if (this.world.isReady()) {
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
        screenX = scaleX(screenX);
        screenY = scaleY(screenY);

        if (world.isMenu()) {
            if (playButton.isTouchDown(screenX, screenY)) {
                world.ready();
                return true;
            }
        }

        rabbit.onRelease();
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

    private int scaleX(int screenX) {
        return (int) (screenX / scaleFactorX);
    }

    //Methods required because touch inputs are based on device screen size not game coordinates
    private int scaleY(int screenY) {
        return (int) (screenY / scaleFactorY);
    }

    public List<Button> getMenuButtons() {
        return menuButtons;
    }

}
