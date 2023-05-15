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
        hitBox.x=x;
        hitBox.y=y;
        hitBox.width=width;
        hitBox.height=height;
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
