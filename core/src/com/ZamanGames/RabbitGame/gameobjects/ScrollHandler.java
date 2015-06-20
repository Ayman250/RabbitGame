package com.ZamanGames.RabbitGame.gameobjects;

import com.ZamanGames.RabbitGame.gameworld.GameWorld;

/**
 * Created by Ayman on 6/6/2015.
 */
public class ScrollHandler {

    private Hill hill1, hill2, hill3;
    private Ground ground1, ground2;
    private Fence fence1, fence2;

    public static int SCROLL_SPEED;
    public static int PIPE_GAP;

    private Rabbit rabbit;

    private int gameWidth;

    public GameWorld world;

    public ScrollHandler(GameWorld world, int gameWidth, int gameHeight, float groundY) {
        this.world = world;
        this.gameWidth = gameWidth;

        rabbit = this.world.getRabbit();

        SCROLL_SPEED = -gameWidth/2;
        PIPE_GAP = gameWidth/2;

        hill1 = new Hill(gameWidth, groundY - 60+10, world.getGameWidth() / 15, 60, SCROLL_SPEED, groundY);
        hill2 = new Hill(hill1.getTailX() + PIPE_GAP, groundY - 100 + 10, world.getGameWidth() / 15, 100, SCROLL_SPEED, groundY);
        hill3 = new Hill(hill2.getTailX() + PIPE_GAP, groundY - 40 + 10, world.getGameWidth() / 15, 40, SCROLL_SPEED, groundY);

        ground1 = new Ground(0, groundY, gameWidth, 90, SCROLL_SPEED, groundY);
        ground2 = new Ground(ground1.getTailX(), groundY, gameWidth, 90, SCROLL_SPEED, groundY);

        fence1 = new Fence(0, groundY-70, gameWidth, 70, SCROLL_SPEED, groundY);
        fence2 = new Fence(ground1.getTailX(), groundY-70, gameWidth, 70, SCROLL_SPEED, groundY);


    }

    public void update(float delta) {

        hill1.update(delta);
        hill2.update(delta);
        hill3.update(delta);

        ground1.update(delta);
        ground2.update(delta);

        fence1.update(delta);
        fence2.update(delta);

        if (ground1.rabbitOn(rabbit)){
            rabbit.changeHeight(ground1.getY());
        }
        else if (ground2.rabbitOn(rabbit)) {
            rabbit.changeHeight(ground2.getY());
        }

        if (hill1.isScrolledLeft()) {
            hill1.reset(hill3.getTailX() + PIPE_GAP);
        }
        else if (hill2.isScrolledLeft()) {
            hill2.reset(hill1.getTailX() + PIPE_GAP);
        }
        else if (hill3.isScrolledLeft()) {
            hill3.reset(hill2.getTailX() + PIPE_GAP);
        }

        if (ground1.isScrolledLeft()) {
            ground1.reset(gameWidth-2);
        }
        else if (ground2.isScrolledLeft()) {
            ground2.reset(gameWidth-2);
        }

        if (fence1.isScrolledLeft()) {
            fence1.reset(gameWidth-2);
        }
        else if (fence2.isScrolledLeft()) {
            fence2.reset(gameWidth-2);
        }
    }

    public Hill getHill1() {
        return hill1;
    }

    public Hill getHill2() {
        return hill2;
    }

    public Hill getHill3() {
        return hill3;
    }

    public Ground getGround1() {
        return ground1;
    }

    public Ground getGround2() {
        return ground2;
    }

    public Fence getFence1() {
        return fence1;
    }

    public Fence getFence2() {
        return fence2;
    }
}
