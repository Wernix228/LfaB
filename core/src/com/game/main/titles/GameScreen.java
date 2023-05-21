package com.game.main.titles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.game.data.DefaultData;
import com.game.entity.Inventory;
import com.game.entity.NPCManager;
import com.game.entity.Player;
import com.game.entity.SolidArea;
import com.game.main.Drawer;
import com.game.main.EventHandler;
import com.game.main.Interface;
import com.game.main.TouchHandler;
import com.game.world.Map;
import com.game.world.items.ItemManager;

public class GameScreen extends ApplicationAdapter {

    public OrthographicCamera camera;
    public OrthographicCamera interfaceCamera;
    public Interface anInterface;
    public TouchHandler touchHandler;
    public Player player;
    public Map map;
    public SolidArea solidArea;
    public Drawer drawer;
    public Viewport viewport;
    public ItemManager itemManager;
    public EventHandler eventHandler;
    public Inventory inventory;
    public NPCManager npcManager;

    @Override
    public void create() {
//		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
        DefaultData.width = Gdx.graphics.getWidth();
        DefaultData.height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1280, 720);
        viewport = new FillViewport(1280, 720, camera);
        interfaceCamera = new OrthographicCamera(DefaultData.width, DefaultData.height);
        anInterface = new Interface();
        touchHandler = new TouchHandler(this, 32 * 3, -32 * 3);
        player = new Player(DefaultData.tileSize, DefaultData.tileSize, this);
        map = new Map(this);
        map.setMap("map01");
        npcManager = new NPCManager(this);
        solidArea = new SolidArea(map, touchHandler, player,npcManager);
        drawer = new Drawer(this);
        itemManager = new ItemManager();
        eventHandler = new EventHandler(this);
        inventory = new Inventory(5,5,this);
        setUpCamera();
    }

    @Override
    public void render() {
        setUpCamera();
        solidArea.render();
        ScreenUtils.clear(0, 0, 0, 1);
        touchHandler.render();
        player.render();
        map.draw();
        drawer.render();
        eventHandler.render();
        inventory.render();
        npcManager.render();
    }

    @Override
    public void dispose() {
        map.dispose();
        player.dispose();
        drawer.dispose();
    }

    public void setUpCamera() {
        drawer.getBatch().setProjectionMatrix(camera.combined);
        drawer.getBatchInterface().setProjectionMatrix(interfaceCamera.combined);
        map.getTileManager().getBatch().setProjectionMatrix(camera.combined);
        camera.position.set(new Vector3(player.getX() + player.getWidth() / 2f, player.getY() + player.getHeight() / 2f, 0));
        interfaceCamera.position.set(new Vector3(DefaultData.width / 2f, DefaultData.height / 2f, 0));
        camera.update();
        interfaceCamera.update();
    }
}
