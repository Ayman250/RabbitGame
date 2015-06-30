package com.ZamanGames.RabbitGame.rhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ayman on 6/6/2015.
 */

public class AssetLoader {

    public static Texture background, ground, fence, rabbitDown, rabbitJumped, dirt, water, spikes, playButtonUp, playButtonDown, tHill;

    public static TextureRegion hill, hillTop, hillBottom;

    public static BitmapFont gameFont;

    public static Music bgMusic;

    public static Preferences prefs;



    public static void load() {
        background = new Texture(Gdx.files.internal("data/background.png"));
        ground = new Texture(Gdx.files.internal("data/ground.png"));
        fence = new Texture(Gdx.files.internal("data/fence.png"));
        rabbitDown = new Texture(Gdx.files.internal("data/PixelRabbit.png"));
        rabbitJumped = new Texture(Gdx.files.internal("data/RabbitUp.png"));
        dirt = new Texture(Gdx.files.internal("data/ground_dirt.png"));
        water = new Texture(Gdx.files.internal("data/water.png"));
        spikes = new Texture(Gdx.files.internal("data/Spikes.png"));
        playButtonUp = new Texture(Gdx.files.internal("data/playButtonUp.png"));
        playButtonDown = new Texture(Gdx.files.internal(("data/playButtonDown.png")));
        tHill = new Texture(Gdx.files.internal("data/hill.png"));

        hillTop = new TextureRegion(tHill, 0, 0, 212, 45);
        hill = new TextureRegion(tHill, 0, 45, 212, 130);
        hillBottom = new TextureRegion(tHill, 0, 175, 212, 42);

        hillTop.flip(false, true);
        hillBottom.flip(false, true);

        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("data/bgMusic.mp3"));

        gameFont = new BitmapFont(Gdx.files.internal("data/gameFont.fnt"), true);

        // Create (or retrieve existing) preferences file
        prefs = Gdx.app.getPreferences("Rabbit Runner");

        fence.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
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
        fence.dispose();
        rabbitDown.dispose();
        rabbitJumped.dispose();
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
