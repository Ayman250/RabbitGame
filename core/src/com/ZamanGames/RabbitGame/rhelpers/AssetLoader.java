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

    public static Texture ground, dirt;

    public static TextureAtlas spriteSheet;

    public static TextureRegion hill, hillTop, hillBottom, rabbitJumped, background, water, spikes, playButtonDown, playButtonUp, dust, cloud1, cloud2, tree;

    public static Animation runningAnimation;

    public static BitmapFont gameFont;

    public static Music bgMusic;

    public static Preferences prefs;



    public static void load() {
        spriteSheet = new TextureAtlas(Gdx.files.internal("data/SpriteSheet.txt"));

        ground = new Texture(Gdx.files.internal("data/ground.png"));

        dirt = new Texture(Gdx.files.internal("data/ground_dirt.png"));

        rabbitJumped = new TextureRegion(spriteSheet.findRegion("Frame01"));

        //temp PlayButtons

        playButtonUp = new TextureRegion(spriteSheet.findRegion("Spikes"));
        playButtonDown = new TextureRegion(spriteSheet.findRegion("Spikes"));
        background = new TextureRegion(spriteSheet.findRegion("background"));
        spikes = new TextureRegion(spriteSheet.findRegion("Spikes"));
        hillTop = new TextureRegion(spriteSheet.findRegion("hillTop"));
        hill = new TextureRegion(spriteSheet.findRegion("hill"));
        hillBottom = new TextureRegion(spriteSheet.findRegion("hillBottom"));
        cloud1 = new TextureRegion(spriteSheet.findRegion("cloud1"));
        cloud2 = new TextureRegion(spriteSheet.findRegion("cloud2"));
        tree = new TextureRegion(spriteSheet.findRegion("TreeTall"));

        TextureRegion[] runFrames = {spriteSheet.findRegion("Frame01"), spriteSheet.findRegion("Frame02"), spriteSheet.findRegion("Frame03"),
                spriteSheet.findRegion("Frame04"), spriteSheet.findRegion("Frame05"), spriteSheet.findRegion("Frame06"),
                spriteSheet.findRegion("Frame07"), spriteSheet.findRegion("Frame08"), spriteSheet.findRegion("Frame09"),  };

        runningAnimation = new Animation(.03f, runFrames);
        runningAnimation.setPlayMode(Animation.PlayMode.LOOP);

        hillTop.flip(false, true);
        hillBottom.flip(false, true);

        background.flip(false, true);


        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("data/bgMusic.mp3"));

        gameFont = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"), true);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("Rabbit Runner");

        ground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        dirt.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        bgMusic.setLooping(true);
        bgMusic.setVolume(0f);

        gameFont.getData().setScale(2f,2f);

        //Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putInteger("highScore", 0);
        }

}

    public void dispose() {
        ground.dispose();
        dirt.dispose();
        bgMusic.dispose();
        gameFont.dispose();
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
