package com.game.world.items;

import java.util.LinkedList;

public class Items {

    LinkedList<Item> items;

    public Items() {
        items = new LinkedList<>();
        setUpItem();
    }

    private void setUpItem() {
        items.add(new Boots(3,3,true));
    }

    public LinkedList<Item> getItems() {
        return items;
    }

}
