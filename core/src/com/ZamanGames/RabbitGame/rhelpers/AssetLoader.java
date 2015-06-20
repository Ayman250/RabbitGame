package com.ZamanGames.RabbitGame.rhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ayman on 6/6/2015.
 */

public class AssetLoader {

    public static Texture hill, background, ground, fence, rabbitDown, rabbitJumped, dirt;
    public static TextureRegion hillTop, hillBottom;



    public static void load() {
        hill = new Texture(Gdx.files.internal("data/hill.png"));
        background = new Texture(Gdx.files.internal("data/background.png"));
        ground = new Texture(Gdx.files.internal("data/ground.png"));
        fence = new Texture(Gdx.files.internal("data/fence.png"));
        rabbitDown = new Texture(Gdx.files.internal("data/PixelRabbit.png"));
        rabbitJumped = new Texture(Gdx.files.internal("data/RabbitUp.png"));
        dirt = new Texture(Gdx.files.internal("data/ground_dirt.png"));


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
