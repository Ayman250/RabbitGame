package com.ZamanGames.RabbitGame.gameworld;

import com.ZamanGames.RabbitGame.gameobjects.Ground;
import com.ZamanGames.RabbitGame.gameobjects.Hill;
import com.ZamanGames.RabbitGame.gameobjects.Rabbit;
import com.ZamanGames.RabbitGame.gameobjects.ScrollHandler;
import com.ZamanGames.RabbitGame.gameobjects.Scrollable;
import com.ZamanGames.RabbitGame.gameobjects.Spike;
import com.ZamanGames.RabbitGame.rhelpers.AssetLoader;
import com.ZamanGames.RabbitGame.rhelpers.InputHandler;
import com.ZamanGames.RabbitGame.ui.Button;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ayman on 6/6/2015.
 */
public class GameRender {
    private GameWorld world;
    private OrthographicCamera cam;
    private Rabbit rabbit;

    private SpriteBatch batch;

    private int gameHeight, gameWidth, groundY;

    private Texture rTexture, background, tGround, tFence, rabbitJumped, rabbitDown, dirt, water, spikes, tPlayButtonUp, tPlayButtonDown;

    private TextureRegion hillTop, hill, hillBottom;


    private Scrollable fence1, fence2, water1, water2;
    private Hill hill1, hill2, hill3, hill4;
    private Ground ground1, ground2;
    private Spike spike1, spike2, spike3;
    private ScrollHandler scroller;
    private ParallaxBackground parallaxBackground;
    private TiledDrawable tiledDrawable;

    private List<Button> menuButtons;

    private Button playButton;

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

        menuButtons = new ArrayList<Button>();

        menuButtons.add(playButton);

        this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
                .getMenuButtons();

        initGameObjects();
        initAssets();

     }

    public void drawHillTops() {
        //draw((x coordinates of top and bottom match, y is shifted by the width (last parameter) so that it sits nicely on top of hill
        //width of hilltop should match width of hill and the height should be experiments with (2*width seems to work well)
        batch.draw(hillTop, hill1.getX(), hill1.getY() - (20), hill1.getWidth(), 20);
        batch.draw(hillTop, hill2.getX(), hill2.getY() - (20), hill2.getWidth(), 20);
        batch.draw(hillTop, hill3.getX(), hill3.getY() - (20), hill3.getWidth(), 20);
    }

    public void drawHills() {
        batch.draw(hill, hill1.getX(), hill1.getY(), hill1.getWidth(), hill1.getHeight());
        batch.draw(hill, hill2.getX(), hill2.getY(), hill2.getWidth(), hill2.getHeight());
        batch.draw(hill, hill3.getX(), hill3.getY(), hill3.getWidth(), hill3.getHeight());
    }

    public void drawHillBottoms() {
        //draw((x coordinates of top and bottom match, y is shifted by the width (last parameter) so that it sits nicely on top of hill
        //width of hilltop should match width of hill and the height should be experiments with (2*width seems to work well)
        batch.draw(hillBottom, hill1.getX(), hill1.getY() + (hill1.getHeight()), hill1.getWidth(), 12);
        batch.draw(hillBottom, hill2.getX(), hill2.getY() + (hill2.getHeight()), hill2.getWidth(), 12);
        batch.draw(hillBottom, hill3.getX(), hill3.getY() + (hill3.getHeight()), hill3.getWidth(), 12);
    }

    public void drawGround() {
        switch (ground1.getGroundHeight()){
            case 0:
                batch.draw(tGround, ground1.getX(), ground1.getY() - 12, ground1.getWidth(), ground1.getHeight() + 12, 0, 0, 8, 1);
                //System.out.println(ground1.getY());
                break;
            case 1:
                batch.draw(tGround, ground1.getX(), ground1.getY()-12, ground1.getWidth(), ground1.getHeight() + 12, 0, 0, 8, 1);
                batch.draw(dirt, ground1.getX(), ground1.getY()+ground1.getHeight(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                //System.out.println("ground1 2");
                break;
            case 2:
                batch.draw(tGround, ground1.getX(), ground1.getY()-12, ground1.getWidth(), ground1.getHeight() + 12, 0, 0, 8, 1);
                batch.draw(dirt, ground1.getX(), ground1.getY()+ground1.getHeight(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                batch.draw(dirt, ground1.getX(), ground1.getY()+2*ground1.getHeight(), ground1.getWidth(), ground1.getHeight(), 0, 0, 8, 1);
                //System.out.println("ground1 3");
                break;
        }



        switch (ground2.getGroundHeight()){
            case 0:
                batch.draw(tGround, ground2.getX(), ground2.getY()-12, ground2.getWidth(), ground2.getHeight() + 12, 0, 0, 8, 1);
                //System.out.println("ground2 1");
                break;
            case 1:
                batch.draw(tGround, ground2.getX(), ground2.getY()-12, ground2.getWidth(), ground2.getHeight() + 12, 0, 0, 8, 1);
                batch.draw(dirt, ground2.getX(), ground2.getY()+ground2.getHeight(), ground2.getWidth(), ground2.getHeight(), 0, 0, 8, 1);
                //System.out.println("ground2 2");
                break;
            case 2:
                batch.draw(tGround, ground2.getX(), ground2.getY()-12, ground2.getWidth(), ground2.getHeight() + 12, 0, 0, 8, 1);
                batch.draw(dirt, ground2.getX(), ground2.getY()+ground2.getHeight(), ground2.getWidth(), ground2.getHeight(), 0, 0, 8, 1);
                batch.draw(dirt, ground2.getX(), ground2.getY()+2*ground2.getHeight(), ground2.getWidth(), ground2.getHeight(), 0, 0, 8, 1);
                //System.out.println("ground2 3");
                break;
        }
    }

    public void drawWater() {
        batch.draw(water, water1.getX(), water1.getY(), water1.getWidth(), water1.getHeight(), 0, 0, 8, 1);
        batch.draw(water, water2.getX(), water2.getY(), water2.getWidth(), water2.getHeight(), 0, 0, 8, 1);

    }

    public void drawFence() {
        batch.draw(tFence, fence1.getX(), fence1.getY(), fence1.getWidth(), fence1.getHeight(), 0, 0, 8, 1);
        batch.draw(tFence, fence2.getX(), fence2.getY(), fence2.getWidth(), fence2.getHeight(), 0, 0, 8, 1);

    }

    public void drawSpikes() {
        batch.draw(spikes, spike1.getX(), spike1.getY(), spike1.getWidth(), -spike1.getHeight());
        batch.draw(spikes, spike2.getX(), spike2.getY(), spike2.getWidth(), -spike2.getHeight());
        batch.draw(spikes, spike1.getX(), spike1.getY(), spike1.getWidth(), -spike1.getHeight());
        batch.draw(spikes, spike2.getX(), spike2.getY(), spike2.getWidth(), -spike2.getHeight());
    }

    public void drawRabbit() {
        if (rabbit.inAir()){
            batch.draw(rabbitJumped, rabbit.getX(), rabbit.getY(), rabbit.getWidth(), -rabbit.getHeight());
        }
        else{
            batch.draw(rabbitDown, rabbit.getX(), rabbit.getY(), rabbit.getWidth(), -rabbit.getHeight());
        }
    }

    private void drawScore() {
        if (world.isGameOver() || world.isHighScore()) {
            if (world.isGameOver()) {
                AssetLoader.gameFont.draw(batch, "GAME OVER",
                        gameWidth / 2 - 100, gameHeight / 2 - 40);
            } else {
                AssetLoader.gameFont.draw(batch, "HIGH SCORE!",
                        gameWidth / 2 - 90, gameHeight / 2 - 40);

                String highScore = "" + AssetLoader.getHighScore();

                int length = ("" + AssetLoader.getHighScore()).length();
                AssetLoader.gameFont.draw(batch, highScore,
                        gameWidth / 2 - (3 * length), gameHeight / 2 - 40 - 50);
                return;
            }
        }
        if (world.isReady()) {
            AssetLoader.gameFont.draw(batch, "TOUCH TO START!",
                    gameWidth / 2 - 190, gameHeight / 2 - 40);

        } else {
            int length = ("" + world.getScore()).length();
            AssetLoader.gameFont.draw(batch, "" + world.getScore() + " m",
                    gameWidth / 2 - (3 * length)*5, gameHeight / 20);
        }
    }

    private void drawMenuUI() {
        for (Button button : menuButtons) {
            button.draw(batch);
        }
    }

    //might use runTime later for animations
    public void render(float delta, float runTime) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, this.gameHeight, this.gameWidth, -this.gameHeight);

        //drawFence();

        //drawHillTops();
        //Temporary Location
        //drawWater();
        drawScore();
        drawGround();
        drawHillTops();
        drawHills();
        drawHillBottoms();
        drawSpikes();
        drawRabbit();


        if (world.isMenu()) {
            drawMenuUI();
        }

        batch.end();

    }

    public void initGameObjects() {
        rabbit = world.getRabbit();
        scroller = world.getScroller();
        hill1 = scroller.getHill1();
        hill2 = scroller.getHill2();
        hill3 = scroller.getHill3();
        hill4 = scroller.getHill4();
        ground1 = scroller.getGround1();
        ground2 = scroller.getGround2();
        fence1 = scroller.getFence1();
        fence2 = scroller.getFence2();
        spike1 = scroller.getSpike1();
        spike2 = scroller.getSpike2();
        water1 = scroller.getWater1();
        water2 = scroller.getWater2();
        //parallaxBackground = new ParallaxBackground();
    }

    public void initAssets() {
        hillTop = AssetLoader.hillTop;
        hill = AssetLoader.hill;
        hillBottom = AssetLoader.hillBottom;
        background = AssetLoader.background;
        tGround  = AssetLoader.ground;
        tFence = AssetLoader.fence;
        rabbitJumped = AssetLoader.rabbitJumped;
        rabbitDown = AssetLoader.rabbitDown;
        dirt = AssetLoader.dirt;
        water = AssetLoader.water;
        spikes = AssetLoader.spikes;
        tPlayButtonUp = AssetLoader.playButtonUp;
        tPlayButtonDown = AssetLoader.playButtonDown;

    }

}
