package com.game.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import com.game.data.DefaultData;
import com.game.entity.Player;
import com.game.main.titles.GameScreen;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Map {
    private final Array<Tile> tiles = new Array<>();
    private final TileManager tileManager;
    private final GameScreen game;
    public Array<Tile> getTiles() {
        return tiles;
    }

    public TileManager getTileManager() {
        return tileManager;
    }

    public Map(GameScreen game) {
        tileManager = new TileManager(this);
        this.game = game;
    }

    public void setMap(String maps) {
        setUpSolid();
        tiles.clear();
        loadMap(levelFile(maps));
        tileManager.createSolidBoxes();
    }

    public void draw() {
        tileManager.render();
    }

    public void dispose() {
        tileManager.dispose();
    }

    private String levelFile(String maps) {
        if (!maps.equals("chunk")) {
            maps = "maps/" + maps + ".txt";
        } else {
            maps = "saves/" + maps + ".dll";
        }
        return maps;
    }

    Boolean[] solidTile = new Boolean[DefaultData.tiles];

    private void setUpSolid() {
        FileHandle file = Gdx.files.internal("solidData.txt");
        InputStream inputStream = file.read();
        Scanner scn = new Scanner(inputStream);
        while (scn.hasNext()) {
            String[] msg = scn.nextLine().split(" ");
            int numTile = Integer.parseInt(msg[0]);
            boolean tileIsSolid = Boolean.parseBoolean(msg[1]);
            solidTile[numTile] = tileIsSolid;
        }
    }

    private void loadMap(String map) {
        Integer[][] arr;
        FileHandle file = Gdx.files.internal(map);
        InputStream inputStream = file.read();
        Scanner scn = new Scanner(inputStream);
        ArrayList<String[]> nums = new ArrayList<>();

        while (scn.hasNext()) {
            nums.add(scn.nextLine().split(" "));
        }
        int columns = nums.get(0).length;
        arr = new Integer[nums.size()][columns];
        Iterator<String[]> iter = nums.iterator();

        for (int i = 0; i < arr.length; i++) {
            String[] s = iter.next();
            for (int j = 0; j < columns; j++) {
                arr[i][j] = Integer.parseInt(s[j]);

                Tile tile = new Tile();
                tile.x = j * DefaultData.tileSize;
                tile.y = -i * DefaultData.tileSize;
                tile.texture = arr[i][j];
                tile.isSolid = solidTile[arr[i][j]];
                tiles.add(tile);
            }
        }
    }
}
