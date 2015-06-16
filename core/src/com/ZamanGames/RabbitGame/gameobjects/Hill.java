package com.ZamanGames.RabbitGame.gameobjects;

import com.ZamanGames.RabbitGame.gameobjects.Scrollable;
import java.util.Random;
/**
 * Created by Ayman on 6/6/2015.
 */
public class Hill extends Scrollable {
    private Random r;

    private float groundY;

    public Hill(float x, float y, int width, int height, float scrollSpeed, float groundY) {
        super(x, y, width, height, scrollSpeed);

        r = new Random();

        this.groundY = groundY;

    }

    @Override
    public void update(float delta) {
        super.update(delta);


    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        height = r.nextInt(90) + 15;
        position.y = groundY - height;
       }
}
