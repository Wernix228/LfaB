package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.game.data.DefaultData;

public class NPC extends Entity {

    private int speed = 4;
    private long time;
    private String direction;
    private String collSide = "nothing";
    private String oldDirection = collSide;
    private boolean collision = false;

    public NPC(int type) {
        switch (type) {
            case 1:
                img = new Texture("textures/player/step1Left.png");
                break;
        }
        x = 100;
        y = -100;
        hitBox.set(x, y, DefaultData.tileSize, DefaultData.tileSize);
        direction = "down";
    }

    public void render() {
        if (Player.isVisible(getX(), getY(), (int) DefaultData.tileSize, (int) DefaultData.tileSize)) {
            time++;
            if (time % 30 == 0) {
                changeDirection();
            }
            if (!direction.equals(oldDirection)) {
                collSide = "nothing";
                collision = false;
            }
            if (!collision) {
                if (direction.equals("top")) y += speed;
                if (direction.equals("bottom")) y -= speed;
                if (direction.equals("left")) x -= speed;
                if (direction.equals("right")) x += speed;
            }
            oldDirection = direction;
        }
        hitBox.set(x, y, DefaultData.tileSize / 2, DefaultData.tileSize / 2);
    }

    private void changeDirection() {
        int i = MathUtils.random(1, 4);
        if (i == 1) direction = "top";
        if (i == 2) direction = "left";
        if (i == 3) direction = "right";
        if (i == 4) direction = "bottom";
    }

    public void setCollSide(String collSide) {
        this.collSide = collSide;
    }

    public int getSpeed() {
        return speed;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
