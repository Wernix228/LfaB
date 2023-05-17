package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;
import com.game.world.items.Item;

import java.util.Arrays;

public class Inventory {
    private Item[][] items;
    private GameScreen gameScreen;
    private int x, y;
    private Texture texture;
    private boolean seeInventory = false;
    private Rectangle inventoryRect;
    private boolean isInventoryFull = false;

    public Inventory(int x, int y, GameScreen gameScreen) {
        items = new Item[x][y];
        this.gameScreen = gameScreen;
        inventoryRect = new Rectangle(Gdx.graphics.getWidth() - 32 * 16, Gdx.graphics.getHeight() - 128 - 32 * 16, 32 * 16, 32 * 16);
        texture = new Texture("textures/interface/inventory.png");
    }

    public void add(Item item) {
        if (y >= 5) isInventoryFull = true;
        else items[x][y] = item;

        x++;
        if (x >= 5) {
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
        if (Gdx.input.justTouched() && Gdx.input.getX() > (1280 - DefaultData.tileSize * 2) && Gdx.input.getY() < (DefaultData.tileSize * 2)) {
            seeInventory = !seeInventory;
        }
        draw(gameScreen.drawer.getBatchInterface());
    }

    private void draw(SpriteBatch batch) {
        if (seeInventory) {
            batch.begin();
            batch.draw(texture, Gdx.graphics.getWidth() - inventoryRect.getWidth(),Gdx.graphics.getHeight() - inventoryRect.getHeight() - 32 * 4, inventoryRect.getWidth(), inventoryRect.getHeight());
            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    if (items[i][j] != null) batch.draw(items[i][j].getTexture(),inventoryRect.getX() + (i * inventoryRect.getWidth() / 5),inventoryRect.getY() + (inventoryRect.getHeight() - (j * inventoryRect.getHeight() / 5) - inventoryRect.getHeight() / 5) , inventoryRect.getWidth() / 5,inventoryRect.getHeight() / 5);
                }
            }
            batch.end();
        }
    }

    public boolean isInventoryFull() {
        return isInventoryFull;
    }
}
