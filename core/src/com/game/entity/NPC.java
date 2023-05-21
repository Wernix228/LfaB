package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.game.data.DefaultData;

public class NPC extends Entity {

    private int speed = 4;
    private long time;
    private String direction;
    private String collSide = "nothing";

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
        time++;
        if (time % 30 == 0) {
            changeDirection();
        }
        if (direction.equals("top")) y += speed;
        if (direction.equals("bottom")) y -= speed;
        if (direction.equals("left")) x -= speed;
        if (direction.equals("right")) x += speed;
        hitBox.set(x, y, DefaultData.tileSize/2, DefaultData.tileSize/2);
        checkColl();
    }

    private void changeDirection() {
        int i = MathUtils.random(1, 4);
        if (i == 1) direction = "top";
        if (i == 2) direction = "left";
        if (i == 3) direction = "right";
        if (i == 4) direction = "bottom";
    }

    private void checkColl() {
        if (collSide.equals(direction)) speed = 0;
        else speed = 4;


    }

    public void setCollSide(String collSide) {
        this.collSide = collSide;
    }
}
