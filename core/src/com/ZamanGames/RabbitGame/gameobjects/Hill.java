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

    public void onReset(float x, float y, int height, float scrollSpeed) {
        position.x = x;
        position.y = y;
        velocity.x = scrollSpeed;
        this.height = height;
        hitBox.x = x;
        hitBox.y = y;

    }

    @Override
    public void reset(float newX, float newY) {
        super.reset(newX, newY);
        height = r.nextInt(100) + 100;
        position.y = groundY - height + 10;
        position.y = newY;
        //System.out.println(newY);
       }

}
