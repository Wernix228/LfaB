package com.game.world.items;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.data.DefaultData;

public class Boots extends Item {
    public Boots(int x, int y, boolean table) {
        this.texture = new Texture("textures/items/boots.png");

        if (table) vector = new Vector2(x * DefaultData.tileSize, -y * DefaultData.tileSize);
        else vector = new Vector2(x, -y);

        rectangle = new Rectangle(vector.x,vector.y, DefaultData.tileSize, DefaultData.tileSize);
    }
}
