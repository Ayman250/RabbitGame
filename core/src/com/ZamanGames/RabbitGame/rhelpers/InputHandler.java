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
//Servs As menu Handler as well
public class InputHandler implements InputProcessor {

    private Rabbit rabbit;
    private GameWorld world;

    private List<Button> menuButtons;

    private float scaleFactorX, scaleFactorY;

    private Button playButton, settingsButton, auddioOnButton, audioOffButton, checkButton, hiScoreButton, pauseButton;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY) {
        this.world = world;
        rabbit = world.getRabbit();

        int midPointY = world.getGameHeight() / 2;

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;

        settingsButton = new Button(world.getGameWidth() / 2 - AssetLoader.settingsGear.getRegionWidth() / 2 - 40, world.getGameHeight() - 300, 80, 80, AssetLoader.settingsGear, AssetLoader.settingsGear);
        hiScoreButton = new Button(world.getGameWidth() / 2 - AssetLoader.hiScores.getRegionWidth() / 2 + 40, world.getGameHeight() - 200, 128, -128, AssetLoader.hiScores, AssetLoader.hiScores);

        playButton = new Button(world.getGameWidth() / 2 - AssetLoader.playButtonUp.getRegionWidth() / 2, world.getGameHeight()/2 - 100, 200, 200,
                AssetLoader.playButton, AssetLoader.playButton);
        auddioOnButton = new Button(world.getGameWidth() / 2 - AssetLoader.audioOn.getRegionWidth() / 2, world.getGameHeight() / 2 + 20, 210, -70, AssetLoader.audioOn, AssetLoader.audioOn);
        audioOffButton = new Button(world.getGameWidth() / 2 - AssetLoader.audioOn.getRegionWidth() / 2, world.getGameHeight() / 2 + 20, 210, -70, AssetLoader.audioOff, AssetLoader.audioOff);
        checkButton = new Button(world.getGameWidth() - 80, world.getGameHeight() + 80, 80, 80, AssetLoader.done, AssetLoader.done);
        pauseButton = new Button(50, 30, 80, 80, AssetLoader.pause, AssetLoader.pause);

        menuButtons = new ArrayList<Button>();
        menuButtons.add(playButton);
        menuButtons.add(auddioOnButton);
        menuButtons.add(checkButton);
        menuButtons.add(hiScoreButton);



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
            if(!settingsButton.isTouchDown(screenX, screenY)) {
                this.world.start();
            }
        }


        if (world.isRunning()) {
            if(pauseButton.isClicked(screenX, screenY)){
                world.pause();
                System.out.print("paused");
            }
            rabbit.onClick();
        } else  if (world.isPaused()) {
            if (settingsButton.isClicked(screenX, screenY)) {
                world.menu();
            } else {
                world.resume();
            }

            System.out.println("resumed");
        }


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

    public Button getSettingsButton() {
        return settingsButton;
    }

    public Button getHiScoreButton() {
        return hiScoreButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getPlayButton() {
        return playButton;
    }

    public Button getAuddioOnButton() {
        return auddioOnButton;
    }

    public Button getCheckButton() {
        return checkButton;
    }
}
