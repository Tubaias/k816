package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.KiitosGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		config.setTitle("gamer");
		config.setWindowedMode(1280, 720);
		config.setResizable(true);
		config.useVsync(false);

		new Lwjgl3Application(new KiitosGame(), config);
	}
}
