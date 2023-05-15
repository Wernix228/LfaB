package com.game.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.game.data.DefaultData;

public class Boots implements Item {

    private Texture texture;
    private Vector2 vector;

    public Boots(int x,int y,boolean table) {
        this.texture = new Texture("textures/items/boots.png");
        if (table) vector = new Vector2(x* DefaultData.tileSize,-y*DefaultData.tileSize);
        else vector = new Vector2(x,-y);
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public Vector2 getVector() {
        return vector;
    }
}
