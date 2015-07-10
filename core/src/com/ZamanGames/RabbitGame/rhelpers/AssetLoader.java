package com.ZamanGames.RabbitGame.rhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ayman on 6/6/2015.
 */

public class AssetLoader {

    public static Texture background, ground, dirt, water, spikes, playButtonUp, playButtonDown, tHill;

    public static TextureAtlas runningAtlas;

    public static TextureRegion hill, hillTop, hillBottom, rabbitJumped;

    public static Animation runningAnimation;

    public static BitmapFont gameFont;

    public static Music bgMusic;

    public static Preferences prefs;



    public static void load() {
        runningAtlas = new TextureAtlas(Gdx.files.internal("data/RabbitAnimation.txt"));

        background = new Texture(Gdx.files.internal("data/background.png"));
        ground = new Texture(Gdx.files.internal("data/ground.png"));
        rabbitJumped = new TextureRegion(runningAtlas.findRegion("Frame01"));
        dirt = new Texture(Gdx.files.internal("data/ground_dirt.png"));
        water = new Texture(Gdx.files.internal("data/water.png"));
        spikes = new Texture(Gdx.files.internal("data/Spikes.png"));
        playButtonUp = new Texture(Gdx.files.internal("data/playButtonUp.png"));
        playButtonDown = new Texture(Gdx.files.internal(("data/playButtonDown.png")));
        tHill = new Texture(Gdx.files.internal("data/hill.png"));

        hillTop = new TextureRegion(tHill, 0, 0, 212, 45);
        hill = new TextureRegion(tHill, 0, 45, 212, 130);
        hillBottom = new TextureRegion(tHill, 0, 175, 212, 42);



        TextureRegion[] runFrames = {runningAtlas.findRegion("Frame01"), runningAtlas.findRegion("Frame02"), runningAtlas.findRegion("Frame03"),
                runningAtlas.findRegion("Frame04"), runningAtlas.findRegion("Frame05"), runningAtlas.findRegion("Frame06"),
                runningAtlas.findRegion("Frame07"), runningAtlas.findRegion("Frame08"), runningAtlas.findRegion("Frame09"),  };

        runningAnimation = new Animation(.05f, runFrames);
        runningAnimation.setPlayMode(Animation.PlayMode.LOOP);

        hillTop.flip(false, true);
        hillBottom.flip(false, true);

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("data/bgMusic.mp3"));

        gameFont = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"), true);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("Rabbit Runner");

        ground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        dirt.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        water.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bgMusic.setLooping(true);
        bgMusic.setVolume(0f);

        gameFont.getData().setScale(2f,2f);

        //Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

}

    public void dispose() {
        //hill.dispose();
        background.dispose();
        ground.dispose();
        water.dispose();
        bgMusic.dispose();
        gameFont.dispose();
        dirt.dispose();
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(int val) {
        prefs.putInteger("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static int getHighScore() {
        return prefs.getInteger("highScore");
    }


}
