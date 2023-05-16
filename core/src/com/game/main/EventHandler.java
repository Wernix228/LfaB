package com.game.main;

import com.game.entity.Player;
import com.game.main.titles.GameScreen;
import com.game.world.items.Item;

public class EventHandler {

    GameScreen gameScreen;

    public EventHandler(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
    }

    public void render() {
        for (int i = 0; i < gameScreen.itemManager.getItems().size(); i++) {
            if (Player.isVisible(gameScreen.itemManager.getItems().get(i).getRectangle())) {
                if (gameScreen.player.getHitBox().overlaps(gameScreen.itemManager.getItems().get(i).getRectangle()) && !gameScreen.itemManager.getItems().get(i).isEventIsActive()) {
                    gameScreen.itemManager.getItems().get(i).setEventIsActive(true);
                    gameScreen.inventory.add(gameScreen.itemManager.getItems().get(i));
                    gameScreen.itemManager.getItems().get(i).setTaken(true);
                }
            }
        }
        //off all events
        for (Item i : gameScreen.itemManager.getItems()) {
            if (Player.isVisible(i.getRectangle())) {
                if (!gameScreen.player.getHitBox().overlaps(i.getRectangle()) && i.isEventIsActive() && !i.isTaken()) {
                    i.setEventIsActive(false);
                }
            }
        }
    }

}
