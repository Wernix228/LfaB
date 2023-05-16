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
        items.add(new Boots(3, 3, true));
        items.add(new Boots(2,3,true));
    }

}
