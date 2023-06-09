package com.game.entity;

import com.badlogic.gdx.utils.Array;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;

public class NPCManager {

    GameScreen game;
    Array<NPC> npcs;

    public NPCManager(GameScreen game) {
        this.game = game;
        npcs = new Array<>();
        setUp();
    }

    public Array<NPC> getNpcs() {
        return npcs;
    }

    public void render() {
        for (NPC n : npcs) {
            n.render();
        }
    }

    private void setUp() {
        for (int i = 0; i < 2; i++) {
            npcs.add(new NPC(1));
        }
    }

}
