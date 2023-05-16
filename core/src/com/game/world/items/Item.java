package com.game.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Item {

    protected Texture texture;
    protected Vector2 vector;
    protected Rectangle rectangle;
    protected boolean eventIsActive = false;
    protected boolean isTaken = false;

    public Texture getTexture() {
        return texture;
    }

    public Vector2 getVector() {
        return vector;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public boolean isEventIsActive() {
        return eventIsActive;
    }

    public void setEventIsActive(boolean eventIsActive) {
        this.eventIsActive = eventIsActive;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}
