package com.game.world;


import com.badlogic.gdx.math.Rectangle;

public class TileSolidBox extends Rectangle {
    private int typeTile;
    private boolean eventTile=false;
    private boolean eventTileWithoutSolidBox=false;

    public int getTypeTile() {
        return typeTile;
    }

    public void setTypeTile(int typeTile) {
        this.typeTile = typeTile;
        if (typeTile == 3) {
            eventTile=true;
            eventTileWithoutSolidBox=true;
        }
        for (int i = 19; i < 32; i++) {
            if (typeTile == i) {
                eventTileWithoutSolidBox = true;
                break;
            }
        }
    }

    public boolean isEventTile() {
        return eventTile;
    }

    public void setEventTile(boolean eventTile) {
        this.eventTile = eventTile;
    }

    public boolean isEventTileWithoutSolidBox() {
        return eventTileWithoutSolidBox;
    }
}
