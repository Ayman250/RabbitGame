package com.ZamanGames.RabbitGame.gameobjects;

import com.ZamanGames.RabbitGame.gameworld.GameWorld;

import java.util.Random;

/**
 * Created by Ayman on 6/6/2015.
 */
public class ScrollHandler {

    private Hill hill1, hill2, hill3, hill4;
    private Ground ground1, ground2, rightGround;
    private Fence fence1, fence2;
    private Spike spike1, spike2, spike3;

    public static int SCROLL_SPEED;
    public static int HILL_GAP;

    private Random r;

    private Rabbit rabbit;

    private Water water1, water2;

    private int gameWidth;

    private float spikeLocation, firstHillLocation, secondhillLocation;

    public GameWorld world;

    public ScrollHandler(GameWorld world, int gameWidth, int gameHeight, float groundY) {
        this.world = world;
        this.gameWidth = gameWidth;

        rabbit = this.world.getRabbit();

        SCROLL_SPEED = -gameWidth/2;
        HILL_GAP = gameWidth/2;

        //Set invisible at first
        hill1 = new Hill(gameWidth, -720 - 222, 90, 140, SCROLL_SPEED, groundY);
        hill2 = new Hill(hill1.getTailX() + HILL_GAP, -720 - 202, 90, 120, SCROLL_SPEED, groundY);
        hill3 = new Hill(hill2.getTailX() + HILL_GAP, -720 - 222, 90, 140, SCROLL_SPEED, groundY);
        hill4 = new Hill(hill3.getTailX() + HILL_GAP, -720 - 202, 90, 120, SCROLL_SPEED, groundY);

        ground1 = new Ground(0, groundY, 1300, 80, SCROLL_SPEED, groundY);
        ground2 = new Ground(ground1.getTailX(), groundY, gameWidth + 20, 80, SCROLL_SPEED, groundY);

        fence1 = new Fence(0, groundY-70, 1300, 70, SCROLL_SPEED, groundY);
        fence2 = new Fence(ground1.getTailX(), groundY-70, gameWidth, 70, SCROLL_SPEED, groundY);

        water1 = new Water(0, groundY, 1300, 70, SCROLL_SPEED);
        water2 = new Water(water1.getTailX(), gameHeight, 1300, 70, SCROLL_SPEED);

        //initialize spikes to be invisble to player
        spike1 = new Spike(0, -200 - 10, 70, 30, SCROLL_SPEED);
        spike2 = new Spike(0, -200 - 10, 70, 30, SCROLL_SPEED);

        r = new Random();

    }

    public void update(float delta) {

        hill1.update(delta);
        hill2.update(delta);
        hill3.update(delta);


        //fence1.update(delta);
        //fence2.update(delta);

        water1.update(delta);
        water2.update(delta);

        if (ground1.rabbitOn(rabbit)) {
            rabbit.changeHeight(ground1.getY());
        } else if (ground2.rabbitOn(rabbit)) {
            rabbit.changeHeight(ground2.getY());
        }
        if (ground1.isScrolledLeft()) {
            //After reset hasSpike is changed to false so it is not rendered
            ground1.reset(ground2.getTailX(), 0);
            //fence1.changeHeight(ground1.getY());
            //50% chance of there being a spiek on the ground
            //Same code logic for spike2
            if (r.nextInt(10) % 2 == 0) {
                ground1.newSpike(spike1);
                spikeLocation = r.nextInt(ground1.getWidth()) + ground1.getX() - spike1.getWidth();
                spike1.reset(spikeLocation, ground1.getY());
            }

            if (r.nextInt(10) % 2 == 0) {
                ground1.firstNewHill(hill1);
                ground1.secondNewHill(hill2);
                firstHillLocation = r.nextInt(ground1.getWidth()) + ground1.getX() - hill1.getWidth();
                secondhillLocation = r.nextInt(ground1.getWidth()) + ground1.getX() - hill1.getWidth();
                hill1.reset(firstHillLocation, ground1.getY() + ground1.getGroundHeight());
                hill2.reset(secondhillLocation, ground1.getY() + ground1.getGroundHeight());
            }

        } else if (ground2.isScrolledLeft()) {
            ground2.reset(ground1.getTailX(), 0);

            if (r.nextInt(10) % 2 == 0) {
                ground2.newSpike(spike2);
                spikeLocation = r.nextInt((int) ground2.getWidth()) + ground2.getX() - spike2.getWidth();
                spike2.reset(spikeLocation, ground2.getY());
            }

            if (r.nextInt(10) % 2 == 0) {
                ground2.firstNewHill(hill3);
                ground2.secondNewHill(hill4);
                firstHillLocation = r.nextInt(ground2.getWidth()) + ground2.getX() - hill3.getWidth();
                secondhillLocation = r.nextInt(ground2.getWidth()) + ground2.getX() - hill4.getWidth();
                hill3.reset(firstHillLocation, ground2.getY() + ground2.getGroundHeight());
                hill4.reset(secondhillLocation, ground2.getY() + ground2.getGroundHeight());
            }

            if (r.nextInt(10) % 2 == 0) {

            }
            //fence2.changeHeight(ground2.getY());
        }


        ground1.update(delta);
        ground2.update(delta);

        spike1.update(delta);
        spike2.update(delta);

        if (water1.isScrolledLeft()) {
            water1.reset(gameWidth - 2, 0);
        }
        else if (water2.isScrolledLeft()) {
            water2.reset(gameWidth - 2, 0);
        }
        //Determine which ground is on the right side and make sure hills spawn according to that one

        //when hill is reset, parameter is passed in to determine how high it will stand now

    }

    public void stop() {
        rabbit.die();
        ground1.stop();
        ground2.stop();
        spike1.stop();
        spike2.stop();
        water1.stop();
        water2.stop();
        world.stopScoring();
    }


    public boolean rabbitCollides() {
        /*if (spike1.collides(rabbit) || spike2.collides(rabbit)) {
            return true;
        }
        else if (ground1.collides(rabbit) || ground2.collides(rabbit)) {
            return true;
        }
        else if (hill1.collides(rabbit) || hill1.collides(rabbit) || hill3.collides(rabbit) || hill4.collides(rabbit)) {
            return true;
        }*/
        return false;
    }

    public void onRestart() {
        ground1.onReset(0, SCROLL_SPEED);
        ground2.onReset(ground1.getTailX()  , SCROLL_SPEED);
        hill1.onReset(gameWidth, 720 -220, 140, SCROLL_SPEED);
        hill2.onReset(hill1.getTailX() + HILL_GAP, 720 - 202, 120, SCROLL_SPEED);
        hill3.onReset(hill2.getTailX() + HILL_GAP, 720 - 220, 140, SCROLL_SPEED);
        hill4.onReset(hill3.getTailX() + HILL_GAP, 720 - 202, 120, SCROLL_SPEED);
        spike1.onReset(0, SCROLL_SPEED);
        spike2.onReset(0, SCROLL_SPEED);
        water1.onReset(0, SCROLL_SPEED);
        water2.onReset(0, SCROLL_SPEED);
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

    public Hill getHill4() {
        return hill4;
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

    public Water getWater1() {
        return water1;
    }

    public Water getWater2() {
        return water2;
    }
}
