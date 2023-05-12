package com.game.main.titles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.data.DefaultData;
import com.game.entity.Player;
import com.game.entity.SolidArea;
import com.game.main.Drawer;
import com.game.main.Interface;
import com.game.main.TouchHandler;
import com.game.world.Map;

public class GameScreen extends ApplicationAdapter {

	public OrthographicCamera camera;
	public OrthographicCamera interfaceCamera;
	public Interface anInterface;
	public TouchHandler touchHandler;
	public Player player;
	public Map map;
	public SolidArea solidArea;
	public Drawer drawer;
	
	@Override
	public void create () {
		Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		DefaultData.cameraWidth = Gdx.graphics.getWidth();
		DefaultData.cameraHeight = Gdx.graphics.getHeight();
		camera = new OrthographicCamera(DefaultData.cameraWidth, DefaultData.cameraHeight);
		interfaceCamera = new OrthographicCamera(DefaultData.cameraWidth, DefaultData.cameraHeight);
		anInterface = new Interface();
		touchHandler = new TouchHandler(this,150, -150);
		player = new Player(DefaultData.tileSize,DefaultData.tileSize, this);
		map = new Map(this);
		map.setMap("map01");
		solidArea = new SolidArea(map, touchHandler, player);
		drawer = new Drawer(this);

		setUpCamera();
	}

	@Override
	public void render () {
		setUpCamera();
		solidArea.render();
		ScreenUtils.clear(0, 0, 0, 1);
		touchHandler.render();
		player.render();
		map.draw(player);
		drawer.render();
	}
	
	@Override
	public void dispose () {
		map.dispose();
		player.dispose();
		drawer.dispose();
	}

	public void setUpCamera(){
		drawer.getBatch().setProjectionMatrix(camera.combined);
		drawer.getBatchInterface().setProjectionMatrix(interfaceCamera.combined);
		map.getTileManager().getBatch().setProjectionMatrix(camera.combined);
		camera.position.set(new Vector3(player.getX() + player.getWidth() / 2f, player.getY() + player.getHeight() / 2f, 0));
		interfaceCamera.position.set(new Vector3(DefaultData.cameraWidth / 2f, DefaultData.cameraHeight / 2f, 0));
		camera.update();
		interfaceCamera.update();
	}
}
