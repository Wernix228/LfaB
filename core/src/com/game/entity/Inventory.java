package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.data.DefaultData;
import com.game.world.items.Item;

import java.util.Arrays;

public class Inventory {
    private Item[][] items;

    private int x, y;

    public Inventory(int x, int y) {
        items = new Item[x][y];
    }

    public void add(Item item) {
        if (y >= 5) System.out.println("fill");
        else items[x][y] = item;

        x++;
        if (x >= 5){
            y++;
            x = 0;
        }
    }

    public void checkInventory() {
        for (Item[] i : items) {
            System.out.println(Arrays.toString(i));
        }
    }

    public void render() {
        if (Gdx.input.justTouched() && Gdx.input.getX() > (1280 - DefaultData.tileSize*2) && Gdx.input.getY() < (DefaultData.tileSize*2)){
            openInventory();
        }
    }
    private void openInventory(){
        for (Item[] i:items) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println();
    }
    private void draw(SpriteBatch batch){
        batch.begin();
//        batch.draw();
        batch.end();
    }

}
