package com.game.world.items;

import java.util.LinkedList;

public class ItemManager {

    LinkedList<Item> items;

    public ItemManager() {
        items = new LinkedList<>();
        setUpItem();
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    private void setUpItem() {
        for (int i = 5; i < 11; i++) {
            for (int j = 5; j < 11; j++) {
                items.add(new Boots(i,j,true));
            }
        }
    }

}
