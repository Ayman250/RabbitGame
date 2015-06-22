package com.ZamanGames.RabbitGame.gameobjects;

import com.ZamanGames.RabbitGame.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Ayman on 6/6/2015.
 */
public class ScrollHandler {

    private Hill hill1, hill2, hill3;
    private Ground ground1, ground2, rightGround;
    private Fence fence1, fence2;
    private Spike spike1, spike2, spike3;

    public static int SCROLL_SPEED;
    public static int PIPE_GAP;

    private Random r;

    private Rabbit rabbit;

    private int gameWidth, spikeLocation;

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

        ground1 = new Ground(0, groundY, 1300, 80, SCROLL_SPEED, groundY);
        ground2 = new Ground(ground1.getTailX(), groundY, gameWidth + 20, 80, SCROLL_SPEED, groundY);

        fence1 = new Fence(0, groundY-70, 1300, 70, SCROLL_SPEED, groundY);
        fence2 = new Fence(ground1.getTailX(), groundY-70, gameWidth, 70, SCROLL_SPEED, groundY);

        //initialize spikes to be invisble to player
        spike1 = new Spike(0, -200 - 10, 70, 50, SCROLL_SPEED);
        spike2 = new Spike(0, -200 - 10, 70, 50, SCROLL_SPEED);

        r = new Random();

    }

    public void update(float delta) {

        //hill1.update(delta);
        //hill2.update(delta);
        //hill3.update(delta);



        //fence1.update(delta);
        //fence2.update(delta);

        if (ground1.rabbitOn(rabbit)){
            rabbit.changeHeight(ground1.getY());
        }
        else if (ground2.rabbitOn(rabbit)) {
            rabbit.changeHeight(ground2.getY());
        }
        if (ground1.isScrolledLeft()) {
            //After reset hasSpike is changed to false so it is not rendered
            ground1.reset(ground2.getTailX(), 0);
            //fence1.changeHeight(ground1.getY());
            //50% chance of there being a spiek on the ground
            //Same code logic for spike2
            if (r.nextInt(10) % 2 == 0) {
                ground1.setSpike(true);
                ground1.newSpike(spike1);
                spikeLocation = r.nextInt();
            }

        }
        else if (ground2.isScrolledLeft()) {
            ground2.reset(ground1.getTailX(), 0);

            if (r.nextInt(10) % 2 == 0) {
                ground2.setSpike(true);
                ground2.newSpike(spike2);
            }
            //fence2.changeHeight(ground2.getY());
        }

        ground1.update(delta);
        ground2.update(delta);

        spike1.update(delta);
        spike2.update(delta);

       /* if (fence1.isScrolledLeft()) {
            fence1.reset(gameWidth-2, 0);
        }
        else if (fence2.isScrolledLeft()) {
            fence2.reset(gameWidth-2, 0);
        }
        //Determine which ground is on the right side and make sure hills spawn according to that one

        //when hill is reset, parameter is passed in to determine how high it will stand now
        if (ground1.isOnRight(ground2)) {
            rightGround = ground1;
        }
        else {
            rightGround = ground2;
        }

        if (hill1.isScrolledLeft()) {
                hill1.reset(hill3.getTailX() + PIPE_GAP, rightGround.getGroundHeight() * rightGround.getHeight());
            }

        else if (hill2.isScrolledLeft()) {
                hill2.reset(hill1.getTailX() + PIPE_GAP, rightGround.getGroundHeight() * rightGround.getHeight());
        }
        else if (hill3.isScrolledLeft()) {
                hill3.reset(hill2.getTailX() + PIPE_GAP, rightGround.getGroundHeight() * rightGround.getHeight());
        }*/
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

    public Spike getSpike1() {
        return spike1;
    }

    public Spike getSpike2() {
        return spike2;
    }

    public Spike getSpike3() {
        return spike3;
    }
}
