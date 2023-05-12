package com.game.main;

import com.badlogic.gdx.Gdx;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;

public class TouchHandler {
    private float deltaX;
    private float deltaY;
    private float xTouch;
    private float yTouch;

    private String side = "right";

    public float collisionDeltaPlusX = 1;
    public float collisionDeltaMinusX = 1;
    public float collisionDeltaPlusY = 1;
    public float collisionDeltaMinusY = 1;

    private float x;
    private float y;
    private final float speed = 5;
    private boolean rewritingTouch = true;
    private final GameScreen game;

    public TouchHandler(GameScreen game, float x, float y) {
        this.game = game;
        this.x = x;
        this.y = y;
    }

    public void render() {

        if (rewritingTouch) {
            xTouch = Gdx.input.getX();
            yTouch = Gdx.input.getY();
        }
        rewritingTouch = false;

        if (xTouch <= 0) xTouch = 1;
        if (yTouch <= 0) yTouch = 1;

        deltaX = -((xTouch - Gdx.input.getX()) / Gdx.input.getX()) * 300;
        deltaY = ((yTouch - Gdx.input.getY()) / Gdx.input.getY()) * 300;

        if (deltaX > 100) deltaX = 100;
        if (deltaX < -100) deltaX = -100;
        if (deltaY > 100) deltaY = 100;
        if (deltaY < -100) deltaY = -100;


        rewritingTouch = !Gdx.input.isTouched();


        if (deltaX >= 0) deltaX *= collisionDeltaPlusX;
        else if (deltaX < 0) deltaX *= collisionDeltaMinusX;


        if (deltaY >= 0) deltaY *= collisionDeltaPlusY;
        else if (deltaY < 0) deltaY *= collisionDeltaMinusY;


        if (Gdx.input.getX() < xTouch) side = "left";
        else if (Gdx.input.getX() > xTouch) side = "right";

        x += (speed / 100) * deltaX;
        y += (speed / 100) * deltaY;

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getXTouch() {
        return xTouch;
    }

    public float getYTouch() {
        return yTouch;
    }

    public boolean isRewritingTouch() {
        return rewritingTouch;
    }

    public float getSpeed() {
        return speed;
    }

    public String getSide() {
        return side;
    }

    public float getDeltaX() {
        return deltaX;
    }

    public float getDeltaY() {
        return deltaY;
    }
}
