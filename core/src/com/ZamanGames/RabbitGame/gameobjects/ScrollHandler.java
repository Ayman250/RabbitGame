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

        hill1 = new Hill(gameWidth, groundY - 60+10, gameWidth / 15, 60, SCROLL_SPEED, groundY);
        hill2 = new Hill(hill1.getTailX() + PIPE_GAP, groundY - 100 + 10, gameWidth/ 15, 100, SCROLL_SPEED, groundY);
        hill3 = new Hill(hill2.getTailX() + PIPE_GAP, groundY - 40 + 10, gameWidth / 15, 40, SCROLL_SPEED, groundY);

        ground1 = new Ground(0, groundY, gameWidth, 50, SCROLL_SPEED, groundY);
        ground2 = new Ground(ground1.getTailX(), groundY, gameWidth, 50, SCROLL_SPEED, groundY);

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

        if (ground1.isScrolledLeft()) {
            ground1.reset(gameWidth-2, 0);
        }
        else if (ground2.isScrolledLeft()) {
            ground2.reset(gameWidth-2, 0);
        }

        if (fence1.isScrolledLeft()) {
            fence1.reset(gameWidth-2, 0);
        }
        else if (fence2.isScrolledLeft()) {
            fence2.reset(gameWidth-2, 0);
        }

        //when hill is reset, parameter is passed in to determine how high it will stand now
        if (hill1.isScrolledLeft()) {
            if (ground1.isOn(hill1.getX())) {
                hill1.reset(hill3.getTailX() + PIPE_GAP, ground1.getGroundHeight()*ground1.getHeight());
            }
            else{
                hill1.reset(hill3.getTailX() + PIPE_GAP, ground2.getGroundHeight()*ground2.getHeight());
            }
        }
        else if (hill2.isScrolledLeft()) {
            if (ground1.isOn(hill2.getX())) {
                hill2.reset(hill1.getTailX() + PIPE_GAP, ground1.getGroundHeight()*ground1.getHeight());
            }
            else{
                hill2.reset(hill1.getTailX() + PIPE_GAP, ground2.getGroundHeight()*ground2.getHeight());
            }
        }
        else if (hill3.isScrolledLeft()) {
            if (ground1.isOn(hill3.getX())) {
                hill3.reset(hill2.getTailX() + PIPE_GAP, ground1.getGroundHeight()*ground1.getHeight());
            } else {
                hill3.reset(hill2.getTailX() + PIPE_GAP, ground2.getGroundHeight()*ground2.getHeight());
            }
        }
        //Second parameter for reset unnecessary except for hills... So 0 is passed in as filler

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
