package com.game.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {

    protected Texture img;
    protected Rectangle hitBox = new Rectangle();

    protected float x;
    protected float y;
    protected static float width;
    protected static float height;

    public void dispose(){
        img.dispose();
    }

    public void setUpHitBox(){
        hitBox.set(x+width/4f, y, width/2f, height/2f);
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public void setIMG(String texture){
        this.img=new Texture(texture);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public static float getWidth() {
        return width;
    }

    public static float getHeight() {
        return height;
    }

    public Texture getImg() {
        return img;
    }
}
