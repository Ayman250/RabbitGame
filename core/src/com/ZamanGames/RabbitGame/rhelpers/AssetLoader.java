package com.ZamanGames.RabbitGame.rhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ayman on 6/6/2015.
 */

public class AssetLoader {

    public static Texture rTexture, hill, background, ground, fence, rabbitDown, rabbitJumepd;
    public static TextureRegion hillTop, hillBottom;



    public static void load() {
        rTexture = new Texture(Gdx.files.internal("data/rabbit.png"));
        hill = new Texture(Gdx.files.internal("data/hill.png"));
        background = new Texture(Gdx.files.internal("data/background.png"));
        ground = new Texture(Gdx.files.internal("data/ground.png"));
        fence = new Texture(Gdx.files.internal("data/fence.png"));
        rabbitDown = new Texture(Gdx.files.internal("data/RabbitDown.png"));
        rabbitJumepd = new Texture(Gdx.files.internal("data/RabbitJumped.png"));


        rTexture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        hillTop = new TextureRegion(hill, 0, 0, 48, 48);
        hillTop.flip(false, true);
        hillBottom = new TextureRegion(hill, 0, 47, 48, 100);
        hillBottom.flip(false, true);
        fence.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        ground.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
}

    public void dispose() {

    }

}
