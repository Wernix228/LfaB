package com.game.main;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.data.DefaultData;
import com.game.main.titles.GameScreen;
import com.game.world.items.Item;

public class Drawer {

    private final SpriteBatch batch = new SpriteBatch();
    private final SpriteBatch batchInterface = new SpriteBatch();
    private final GameScreen game;

    public Drawer(GameScreen game) {
        this.game = game;
    }

    public void render() {
        draw();
        drawInterface();
    }

    public void dispose() {
        batch.dispose();
        batchInterface.dispose();
    }

    private void draw() {
        batch.begin();

        batch.draw(game.player.getImg(), game.player.getX(), game.player.getY(), game.player.getWidth(), game.player.getHeight());
        for (Item item : game.itemManager.getItems()) {
            if (!item.isTaken()) batch.draw(item.getTexture(), item.getVector().x + DefaultData.tileSize / 4, item.getVector().y + DefaultData.tileSize / 4, DefaultData.tileSize / 2, DefaultData.tileSize / 2);
        }

        batch.end();
    }

    private void drawInterface() {
        batchInterface.begin();
        if (!game.touchHandler.isRewritingTouch()) {
            batchInterface.draw(game.anInterface.textures[0],
                    game.touchHandler.getXTouch() - DefaultData.tileSize * 4 / 2,
                    Gdx.graphics.getHeight() - game.touchHandler.getYTouch() - DefaultData.tileSize * 4 / 2,
                    DefaultData.tileSize * 4, DefaultData.tileSize * 4);
        }
        batchInterface.draw(game.anInterface.textures[1], Gdx.graphics.getWidth()-DefaultData.tileSize*2, Gdx.graphics.getHeight()-DefaultData.tileSize*2,DefaultData.tileSize*2,DefaultData.tileSize*2);
        batchInterface.end();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public SpriteBatch getBatchInterface() {
        return batchInterface;
    }
}
