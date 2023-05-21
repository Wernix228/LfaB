package com.game.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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
    private int itemNum;

    public Inventory(int x, int y, GameScreen gameScreen) {
        items = new Item[x][y];
        this.gameScreen = gameScreen;
        inventoryRect = new Rectangle(Gdx.graphics.getWidth() - 32 * 16, Gdx.graphics.getHeight() - 128 - 32 * 16, 32 * 16, 32 * 16);
        texture = new Texture("textures/interface/inventory.png");
    }

    public void add(Item item) {
        itemNum++;
        if (itemNum > 24) isInventoryFull = true;
        items[x][y] = item;

        x++;
        if (x >= 5) {
            y++;
            x = 0;
        }
    }

    public void checkInventory() {
        System.out.println();
        for (Item[] i : items) {
            System.out.println(Arrays.toString(i));
        }
        System.out.println();
    }

    public void render() {
        if (Gdx.input.justTouched() && Gdx.input.getX() > (1280 - DefaultData.tileSize * 2) && Gdx.input.getY() < (DefaultData.tileSize * 2)) {
            seeInventory = !seeInventory;
            checkInventory();
        }
        draw(gameScreen.drawer.getBatchInterface());
        touchItem();
    }

    public boolean isInventoryFull() {
        return isInventoryFull;
    }

    private void draw(SpriteBatch batch) {
        if (seeInventory) {
            batch.begin();
            batch.draw(texture, Gdx.graphics.getWidth() - inventoryRect.getWidth(), Gdx.graphics.getHeight() - inventoryRect.getHeight() - DefaultData.tileSize * 2, inventoryRect.getWidth(), inventoryRect.getHeight());
            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    if (items[i][j] != null)
                        batch.draw(items[i][j].getTexture(), inventoryRect.getX() + (i * inventoryRect.getWidth() / 5), inventoryRect.getY() + (inventoryRect.getHeight() - (j * inventoryRect.getHeight() / 5) - inventoryRect.getHeight() / 5), inventoryRect.getWidth() / 5, inventoryRect.getHeight() / 5);
                }
            }
            batch.end();
        }
    }

    private Vector2 touchPoint() {
        return new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
    }

    private void touchItem() {
        if (Gdx.input.justTouched()) {
            if (touchPoint().x > Gdx.graphics.getWidth() - inventoryRect.getWidth() && touchPoint().y > Gdx.graphics.getHeight() - inventoryRect.getHeight() - DefaultData.tileSize * 2 && touchPoint().y < Gdx.graphics.getHeight() - DefaultData.tileSize * 2 && seeInventory) {

                if (touchPoint().x > Gdx.graphics.getWidth() - inventoryRect.getWidth() - inventoryRect.getWidth() / 5 && touchPoint().x > Gdx.graphics.getWidth() - inventoryRect.getWidth() - inventoryRect.getWidth() / 4) System.out.println("1");


            }
        }
    }

}
