package com.ZamanGames.RabbitGame.gameworld;

import com.ZamanGames.RabbitGame.gameobjects.Ground;
import com.ZamanGames.RabbitGame.gameobjects.Hill;
import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameobjects.ScrollHandler;
import com.ZamanGames.RabbitGame.gameobjects.Scrollable;
import com.ZamanGames.RabbitGame.rhelpers.AssetLoader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Ayman on 6/6/2015.
 */
public class GameRender {
    private GameWorld world;
    private OrthographicCamera cam;
    private Rabbit rabbit;

    private SpriteBatch batch;

    private int gameHeight, gameWidth, groundY;

    private Texture rTexture, background, tGround, tFence, rabbitJumped, rabbitDown;

    private Scrollable hill1, hill2, hill3, fence1, fence2;
    private Ground ground1, ground2;
    private ScrollHandler scroller;

    private TextureRegion hillTop, hillBottom;

    public GameRender(GameWorld world, int gameHeight, int gameWidth, int groundY) {
        this.world = world;
        this.gameHeight = gameHeight;
        this.gameWidth = gameWidth;
        this.groundY = groundY;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, this.gameWidth, this.gameHeight);
        //cam.setToOrtho(true, 1080, 1920);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
     }

    public void drawHillTops() {
        //draw((x coordinates of top and bottom match, y is shifted by the width (last parameter) so that it sits nicely on top of hill
        //width of hilltop should match width of hill and the height should be experiments with (2*width seems to work well)
        batch.draw(hillTop, hill1.getX(), hill1.getY()-(hill1.getWidth()*2), hill1.getWidth(), hill1.getWidth()*2);
        batch.draw(hillTop, hill2.getX(), hill2.getY()-(hill2.getWidth()*2), hill2.getWidth(), hill2.getWidth()*2);
        batch.draw(hillTop, hill3.getX(), hill3.getY()-(hill3.getWidth()*2), hill3.getWidth(), hill3.getWidth()*2);
    }

    public void drawHillBottoms() {
        batch.draw(hillBottom, hill1.getX(), hill1.getY(), hill1.getWidth(), hill1.getHeight());
        batch.draw(hillBottom, hill2.getX(), hill2.getY(), hill2.getWidth(), hill2.getHeight());
        batch.draw(hillBottom, hill3.getX(), hill3.getY(), hill3.getWidth(), hill3.getHeight());
    }

    public void drawGround() {
        switch (ground1.getGroundHeight()){
            case 1: batch.draw(tGround, ground1.getX(), ground1.getY(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                break;
            case 2: batch.draw(tGround, ground1.getX(), ground1.getY(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                break;
            case 3: batch.draw(tGround, ground1.getX(), ground1.getY(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                break;
        }


        batch.draw(tGround, ground2.getX(), ground2.getY(), ground2.getWidth(), ground2.getHeight(), 0, 0, 8, 1);
    }

    public void drawFence() {
        batch.draw(tFence, fence1.getX(), fence1.getY(), fence1.getWidth(), fence1.getHeight(), 0, 0, 8, 1);
        batch.draw(tFence, fence2.getX(), fence2.getY(), fence2.getWidth(), fence2.getHeight(), 0, 0, 8, 1);

    }

    public void drawRabbit() {
        if (rabbit.inAir()){
            batch.draw(rabbitJumped, rabbit.getX(), rabbit.getY(), rabbit.getWidth(), -rabbit.getHeight());
        }
        else{
            batch.draw(rabbitDown, rabbit.getX(), rabbit.getY(), rabbit.getWidth(), -rabbit.getHeight());
        }
    }

    public void render(float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, this.gameHeight, this.gameWidth, -this.gameHeight);
        drawFence();
        drawHillBottoms();
        drawHillTops();
        drawGround();
        drawRabbit();
        batch.end();
    }

    public void initGameObjects() {
        rabbit = world.getRabbit();
        scroller = world.getScroller();
        hill1 = scroller.getHill1();
        hill2 = scroller.getHill2();
        hill3 = scroller.getHill3();
        ground1 = scroller.getGround1();
        ground2 = scroller.getGround2();
        fence1 = scroller.getFence1();
        fence2 = scroller.getFence2();
    }

    public void initAssets() {
        hillTop = AssetLoader.hillTop;
        hillBottom = AssetLoader.hillBottom;
        background = AssetLoader.background;
        tGround  = AssetLoader.ground;
        tFence = AssetLoader.fence;
        rabbitJumped = AssetLoader.rabbitJumped;
        rabbitDown = AssetLoader.rabbitDown;

    }

}
