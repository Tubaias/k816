package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;

public class KiitosGame extends ApplicationAdapter {
	private Drawer drawer;
	private Player player;
	private World world;
	private InputHandler inputHandler;
	
	@Override
	public void create() {
		player = new Player();
		world = new World();
		drawer = new Drawer(player, world);

		inputHandler = new InputHandler(player, world, drawer.getViewport());

		world.createLammas(100, 100, 1);
		world.createLammas(500, 500, 2);
	}

	@Override
	public void render() {
		inputHandler.handleInputs();

		world.tick();
		player.updateTargets();

		drawer.drawFrame();
	}
	
	@Override
	public void dispose () {
		//img.dispose();
		drawer.dispose();
	}
}
