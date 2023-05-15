package com.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.game.data.DefaultData;
import com.game.entity.Player;
import com.game.world.items.Item;
import com.game.world.items.Items;

public class TileManager {
    private final Map map;
    private final SpriteBatch batch = new SpriteBatch();
    private final Sprite[] sprite = new Sprite[DefaultData.tiles];
    private final Array<TileSolidBox> solidBoxes = new Array<>();
    private final Items items;


    public TileManager(Map map) {
        this.map = map;
        setTextures();
        items  = new Items();
    }

    private void setTextures() {
        for (int i = 0; i < sprite.length; i++) {
            sprite[i] = new Sprite(new Texture(getTileImage(i)));
        }
    }
    public void createSolidBoxes() {
        TileSolidBox solidBox;
        for (int i = 0; i < map.getTiles().size; i++) {
            if (map.getTiles().get(i).isSolid) {
                solidBox = new TileSolidBox();
                solidBox.set(map.getTiles().get(i).x, map.getTiles().get(i).y, DefaultData.tileSize, DefaultData.tileSize);
                solidBox.setTypeTile(map.getTiles().get(i).texture);
                solidBoxes.add(solidBox);
            }
        }
    }
    public void render() {
        batch.begin();
        for (int i = 0; i < map.getTiles().size; i++) {
            if (Player.isVisible(map.getTiles().get(i).x, map.getTiles().get(i).y, 100, 100)) {
                batch.draw(sprite[map.getTiles().get(i).texture], map.getTiles().get(i).x, map.getTiles().get(i).y, DefaultData.tileSize, DefaultData.tileSize);
                for (Item item:items.getItems()) {
                    batch.draw(item.getTexture(),item.getVector().x+DefaultData.tileSize/4,item.getVector().y+DefaultData.tileSize/4,DefaultData.tileSize/2,DefaultData.tileSize/2);
                }
            }
        }
        batch.end();
    }

    public void dispose() {
        batch.dispose();
    }

    private String getTileImage(int texture) {
        if (texture < 10) {
            return "textures/tiles/00" + texture + ".png";
        } else if (texture < 100) {
            return "textures/tiles/0" + texture + ".png";
        } else {
            return "textures/tiles/" + texture + ".png";
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Array<TileSolidBox> getSolidBoxes() {
        return solidBoxes;
    }
}
